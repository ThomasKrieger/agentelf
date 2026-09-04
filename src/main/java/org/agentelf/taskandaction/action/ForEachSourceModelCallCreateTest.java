package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.SourceModel;
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
public class ForEachSourceModelCallCreateTest {

    private final ApplyTemplate applyTemplate;
    private final TaskAndActionFactory taskAndActionFactory;

    @Action(arguments = {"sourceModel", "taskMap"})
    public void forEachModelCallCreateTest(SourceModel sourceModel,
                                           Map<String, TaskDescription> taskMap) throws Exception {
        for (ReferenceTypeHandle handle : sourceModel.getAllTypeHandles()) {
            AbstractTypeSource type = sourceModel.getType(handle);

            Optional<String> unitTestPrompt = type.getUnitTestPrompt(applyTemplate);
            if(unitTestPrompt.isEmpty()) {
                continue;
            }

            String prompt = sourceModel.createTextForAllTypes()
                    + System.lineSeparator()
                    + unitTestPrompt.get();

            RunVariables runVariables = new RunVariables();
            runVariables.setCurrentType(type);
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
