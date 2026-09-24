package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.model.tosource.TypeToSource;
import org.agentelf.mustache.ApplyTemplate;
import org.agentelf.taskandaction.RunVariables;
import org.agentelf.taskandaction.task.Task;
import org.agentelf.type.ReferenceTypeRepo;
import org.agentelf.yaml.TaskAndActionFactory;
import org.agentelf.yaml.TaskDescription;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ForEachSourceModelCallCreateTest {

    private final ApplyTemplate applyTemplate;
    private final TaskAndActionFactory taskAndActionFactory;

    @Action(arguments = {"toSourceModel", "taskMap" , "referenceTypeRepo"})
    public void forEachModelCallCreateTest(ToSourceModel toSourceModel,
                                           Map<String, TaskDescription> taskMap,
                                           ReferenceTypeRepo referenceTypeRepo) throws Exception {
        for (ReferenceTypeHandle handle : toSourceModel.getAllTypeHandles()) {
            TypeToSource type = toSourceModel.getType(handle);
            RunVariables runVariables = new RunVariables();
            runVariables.setReferenceTypeRepo(referenceTypeRepo);
            runVariables.setCurrentType(type);
            runVariables.setToSourceModel(toSourceModel);

            Map<String,Object> context = new HashMap<>();
            context.put("name" , handle.toString());

            String prompt = toSourceModel.asPrompt() +
                    applyTemplate.apply(context,"promptUnitTest.mustache");

            runVariables.setPrompt(prompt);
            runVariables.setClassName(handle.name() + "Test");
            runVariables.setPackageName(handle.packageName());

            TaskDescription taskDescription = taskMap.get("createUnitTestFromToSourceModel");
            if (taskDescription != null) {
                Task task = taskDescription.build(taskAndActionFactory);
                task.execute(runVariables);
            }
        }
    }

}
