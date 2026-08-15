package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.model.handle.ReferenceTypeHandle;
import org.agentelf.model.intermediate.AbstractTypeIntermediate;
import org.agentelf.model.intermediate.ModelIntermediate;
import org.agentelf.mustache.ApplyTemplate;
import org.agentelf.taskandaction.RunVariables;
import org.agentelf.taskandaction.task.Task;
import org.agentelf.yaml.TaskAndActionFactory;
import org.agentelf.yaml.TaskDescription;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ForEachModelCallCreateClass {


    private final TaskAndActionFactory taskAndActionFactory;
    private final ApplyTemplate applyTemplate;

    /**
     * Iterates over modelIntermediate.getAllTypeHandles
     * creates a new RunVariables
     *     create prompt as modelIntermediate.createTextForTypesExcept and
     *        ApplyTemplate.applyToIntermediateType to modelIntermediate.getType
     *     setPrompt to prompt
     *     setClass and setPackageName from handle
     *     get the task createClass from the taskMap snd execute the task
     *
     * @param modelIntermediate The Intermediate Model
     * @param taskMap The Task Map
     */
    @Action(arguments = {"modelIntermediate", "taskMap"})
    public void forEachModelCallCreateClass(ModelIntermediate modelIntermediate,
                                            Map<String, TaskDescription> taskMap) throws Exception {
        for (ReferenceTypeHandle handle : modelIntermediate.getAllTypeHandles()) {
            AbstractTypeIntermediate type = modelIntermediate.getType(handle);

            type.setIncludePrompt(true);

            String prompt = modelIntermediate.createTextForTypesExcept(handle)
                    + System.lineSeparator()
                    + applyTemplate.applyToIntermediateType(type);

            type.setIncludePrompt(false);

            RunVariables runVariables = new RunVariables();
            runVariables.setPrompt(prompt);
            runVariables.setClassName(handle.name());
            runVariables.setPackageName(handle.packageName());

            TaskDescription taskDescription = taskMap.get("createClass");
            if (taskDescription != null) {
                Task task = taskDescription.build(taskAndActionFactory);
                task.execute(runVariables);
            }
            modelIntermediate.setGeneratedType(handle,runVariables.getCreatedClass());
        }
    }

}