package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.model.tosource.TypeToSource;
import org.agentelf.mustache.ApplyTemplate;
import org.agentelf.taskandaction.RunVariables;
import org.agentelf.taskandaction.task.Task;
import org.agentelf.type.TypeRepo;
import org.agentelf.yaml.TaskAndActionFactory;
import org.agentelf.yaml.TaskDescription;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ForEachTypeCreateVMLensTest {

    private final ApplyTemplate applyTemplate;
    private final TaskAndActionFactory taskAndActionFactory;

    @Action(arguments = {"toSourceModel", "taskMap" , "typeRepo"})
    public void forEachTypeCreateVMLensTest(ToSourceModel toSourceModel,
                                           Map<String, TaskDescription> taskMap,
                                           TypeRepo typeRepo) throws Exception {

        Set<String> alreadyProcessed = readSet(Path.of("alreadyProcessed.txt"));

        int i = 0;

        for (ReferenceTypeHandle handle : toSourceModel.getAllTypeHandles()) {
            if(handle.name().equals("IndexWriter") || handle.name().equals("NRTCachingDirectory")) {
                continue;
            }

            if(alreadyProcessed.contains(handle.fullQualifiedName())) {
                continue;
            }

            alreadyProcessed.add(handle.fullQualifiedName());

            TypeToSource type = toSourceModel.getType(handle);

                RunVariables runVariables = new RunVariables();
                runVariables.setTypeRepo(typeRepo);
                runVariables.setCurrentType(type);
                runVariables.setToSourceModel(toSourceModel);

                Map<String,Object> context = new HashMap<>();
                context.put("name" , handle.fullQualifiedName());
                context.put("testName" , handle.name() + "Test");
                String prompt = type.getSourceAsString() +
                        applyTemplate.apply(context,"promptVMLensTest.mustache");

                runVariables.setPrompt(prompt);
                runVariables.setClassName(handle.name() + "Test");
                runVariables.setPackageName(handle.packageName());

                TaskDescription taskDescription = taskMap.get("createUnitTestFromToSourceModel");
                Task task = taskDescription.build(taskAndActionFactory);
                task.execute(runVariables);

                i++;
                if(i > 1) {
                    break;
                }
        }
        writeSet(Path.of("alreadyProcessed.txt"),alreadyProcessed);
    }

    private static void writeSet(Path path, Set<String> strings) throws IOException {
        Files.write(path, strings);
    }

    private static HashSet<String> readSet(Path path) throws IOException {
        return new HashSet<>(Files.readAllLines(path));
    }


}
