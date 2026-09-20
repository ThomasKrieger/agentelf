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
public class CallActionCreateClassFromSource {

    private final TaskAndActionFactory taskAndActionFactory;

    @Action(arguments = {"packageName", "className","prompt", "taskMap"},returnVariable = "createdClass")
    public String callActionCreateClassFromSource(String packageName,
                                                String className,
                                                String prompt,
                                            Map<String, TaskDescription> taskMap) throws Exception {
            RunVariables runVariables = new RunVariables();
            runVariables.setPrompt(prompt);
            runVariables.setClassName(className);
            runVariables.setPackageName(packageName);
            TaskDescription taskDescription = taskMap.get("createClassFromSource");
            Task task = taskDescription.build(taskAndActionFactory);
            task.execute(runVariables);
            return runVariables.getCreatedClass();
    }

}
