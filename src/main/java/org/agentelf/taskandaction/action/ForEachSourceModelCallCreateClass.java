package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.SourceModel;
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
     * Iterates over sourceModel.getAllTypeHandles
     * creates a new RunVariables
     *     create prompt as sourceModel.createTextForTypesExcept and
     *        ApplyTemplate.applyToIntermediateType to sourceModel.getType
     *     setPrompt to prompt
     *     setClass and setPackageName from handle
     *     get the task createClass from the taskMap snd execute the task
     *
     * @param sourceModel The Intermediate Model
     * @param taskMap The Task Map
     */
    @Action(arguments = {"sourceModel", "taskMap", "referenceTypeRepo"})
    public void forEachModelCallCreateClass(SourceModel sourceModel,
                                            Map<String, TaskDescription> taskMap,
                                            ReferenceTypeRepo referenceTypeRepo) throws Exception {
        for (ReferenceTypeHandle handle : sourceModel.getAllTypeHandles()) {
            AbstractTypeSource type = sourceModel.getType(handle);
            RunVariables runVariables = new RunVariables();
            runVariables.setReferenceTypeRepo(referenceTypeRepo);
            runVariables.setCurrentType(type);
            runVariables.setSourceModel(sourceModel);
            runVariables.setPrompt("");
            runVariables.setClassName(handle.name());
            runVariables.setPackageName(handle.packageName());

            TaskDescription taskDescription = taskMap.get("createClass");
            Task task = taskDescription.build(taskAndActionFactory);
            task.execute(runVariables);

            sourceModel.setGeneratedType(handle,runVariables.getCreatedClass());
        }
    }

}