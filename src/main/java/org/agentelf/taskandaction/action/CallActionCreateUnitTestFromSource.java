package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.taskandaction.RunVariables;
import org.agentelf.taskandaction.task.Task;
import org.agentelf.yaml.TaskAndActionFactory;
import org.agentelf.yaml.TaskDescription;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CallActionCreateUnitTestFromSource {

    private final TaskAndActionFactory taskAndActionFactory;

    @Action(arguments = {"packageName", "className","createdClass", "taskMap"})
    public void callActionCreateClassFromSource(String packageName,
                                                  String className,
                                                  String createdClass,
                                                  Map<String, TaskDescription> taskMap) throws Exception {
        RunVariables runVariables = new RunVariables();
        runVariables.setPrompt(createdClass);
        runVariables.setClassName(className);
        runVariables.setPackageName(packageName);
        TaskDescription taskDescription = taskMap.get("createUnitTestFromSource");
        Task task = taskDescription.build(taskAndActionFactory);
        task.execute(runVariables);
    }

}
