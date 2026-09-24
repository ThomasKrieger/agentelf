package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.tosource.TypeToSource;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.taskandaction.RunVariables;
import org.agentelf.taskandaction.task.Task;
import org.agentelf.type.ReferenceTypeRepo;
import org.agentelf.yaml.TaskAndActionFactory;
import org.agentelf.yaml.TaskDescription;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ForEachSourceModelCallCreateClass {

    private final TaskAndActionFactory taskAndActionFactory;
    private final SaveClass saveClass;

    @Action(arguments = {"toSourceModel", "taskMap", "referenceTypeRepo"})
    public void forEachSourceModelCallCreateClass(ToSourceModel toSourceModel,
                                            Map<String, TaskDescription> taskMap,
                                            ReferenceTypeRepo referenceTypeRepo) throws Exception {
        for (ReferenceTypeHandle handle : toSourceModel.getAllTypeHandles()) {
            TypeToSource type = toSourceModel.getType(handle);
            Optional<String> prompt = type.createPrompt();
            if(prompt.isPresent()) {
                RunVariables runVariables = new RunVariables();
                runVariables.setReferenceTypeRepo(referenceTypeRepo);
                runVariables.setCurrentType(type);
                runVariables.setToSourceModel(toSourceModel);
                String builder = toSourceModel.asPrompt() +
                        prompt;
                runVariables.setPrompt(builder);
                runVariables.setClassName(handle.name());
                runVariables.setPackageName(handle.packageName());
                TaskDescription taskDescription = taskMap.get("createClassFromToSourceModel");
                Task task = taskDescription.build(taskAndActionFactory);
                task.execute(runVariables);
                type.setSource(runVariables.getCreatedClass());
            } else {
                saveClass.saveClass(type.getSource(),handle.packageName(), handle.name());
            }
        }
    }

}