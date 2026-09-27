package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.model.tosource.TypeSourceString;
import org.agentelf.model.tosource.TypeToSource;
import org.agentelf.taskandaction.RunVariables;
import org.agentelf.taskandaction.task.Task;
import org.agentelf.type.TypeRepo;
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

    @Action(arguments = {"toSourceModel", "taskMap", "typeRepo"})
    public void forEachSourceModelCallCreateClass(ToSourceModel toSourceModel,
                                            Map<String, TaskDescription> taskMap,
                                            TypeRepo typeRepo) throws Exception {

        for (ReferenceTypeHandle handle : toSourceModel.getAllTypeHandles()) {
            TypeToSource type = toSourceModel.getType(handle);
            Optional<String> prompt = type.createPrompt();
            if(prompt.isPresent()) {
                RunVariables runVariables = new RunVariables();
                runVariables.setTypeRepo(typeRepo);
                runVariables.setCurrentType(type);
                runVariables.setToSourceModel(toSourceModel);
                String builder = toSourceModel.asPrompt() +
                        prompt.get();
                runVariables.setPrompt(builder);
                runVariables.setClassName(handle.name());
                runVariables.setPackageName(handle.packageName());
                TaskDescription taskDescription = taskMap.get("createClassFromToSourceModel");
                Task task = taskDescription.build(taskAndActionFactory);
                task.execute(runVariables);
                type.setTypeSource(new TypeSourceString(runVariables.getCreatedClass()));
                typeRepo.getReferenceTypeRepo().put(handle,runVariables.getCreatedClass());
            } else {
                saveClass.saveClass(type.getSourceAsString(),handle.packageName(), handle.name());
            }
        }
    }

}