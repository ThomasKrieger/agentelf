package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.tosource.AbstractTypeToSource;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.taskandaction.RunVariables;
import org.agentelf.taskandaction.task.Task;
import org.agentelf.type.ReferenceTypeRepo;
import org.agentelf.yaml.TaskAndActionFactory;
import org.agentelf.yaml.TaskDescription;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ForEachSourceModelCallCreateClass {

    private final TaskAndActionFactory taskAndActionFactory;

    /**
     * Iterates over toSourceModel.getAllTypeHandles
     * creates a new RunVariables
     *     create prompt as toSourceModel.createTextForTypesExcept fields
     *        ApplyTemplate.applyToIntermediateType to toSourceModel.getType
     *     setPrompt to prompt
     *     setClass fields setPackageName from handle
     *     get the task createClass from the taskMap snd execute the task
     *
     * @param toSourceModel The Intermediate Model
     * @param taskMap The Task Map
     */
    @Action(arguments = {"toSourceModel", "taskMap", "referenceTypeRepo"})
    public void forEachModelCallCreateClass(ToSourceModel toSourceModel,
                                            Map<String, TaskDescription> taskMap,
                                            ReferenceTypeRepo referenceTypeRepo) throws Exception {
        for (ReferenceTypeHandle handle : toSourceModel.getAllTypeHandles()) {
            AbstractTypeToSource type = toSourceModel.getType(handle);
            RunVariables runVariables = new RunVariables();
            runVariables.setReferenceTypeRepo(referenceTypeRepo);
            runVariables.setCurrentType(type);
            runVariables.setToSourceModel(toSourceModel);
            runVariables.setPrompt("");
            runVariables.setClassName(handle.name());
            runVariables.setPackageName(handle.packageName());

            TaskDescription taskDescription = taskMap.get("createClassFromToSourceModel");
            Task task = taskDescription.build(taskAndActionFactory);
            task.execute(runVariables);

            toSourceModel.setGeneratedType(handle,runVariables.getCreatedClass());
        }
    }

}