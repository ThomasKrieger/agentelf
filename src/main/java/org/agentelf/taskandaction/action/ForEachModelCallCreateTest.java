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
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ForEachModelCallCreateTest {

    private final ApplyTemplate applyTemplate;
    private final TaskAndActionFactory taskAndActionFactory;

    @Action(arguments = {"modelIntermediate", "taskMap"})
    public void forEachModelCallCreateTest(ModelIntermediate modelIntermediate,
                                           Map<String, TaskDescription> taskMap) throws Exception {
        for (ReferenceTypeHandle handle : modelIntermediate.getAllTypeHandles()) {
            AbstractTypeIntermediate type = modelIntermediate.getType(handle);

            Optional<String> unitTestPrompt = type.getUnitTestPrompt(applyTemplate);
            if(unitTestPrompt.isEmpty()) {
                continue;
            }

            String prompt = modelIntermediate.createTextForAllTypes()
                    + System.lineSeparator()
                    + unitTestPrompt.get();

            RunVariables runVariables = new RunVariables();
            runVariables.setPrompt(prompt);
            runVariables.setClassName(handle.name() + "Test");
            runVariables.setPackageName(handle.packageName());

            TaskDescription taskDescription = taskMap.get("createUnitTest");
            if (taskDescription != null) {
                Task task = taskDescription.build(taskAndActionFactory);
                task.execute(runVariables);
            }
        }
    }

}
