package org.agentelf.cli;

import lombok.AllArgsConstructor;
import org.agentelf.taskandaction.task.Task;
import org.agentelf.taskandaction.task.TaskVariables;
import org.agentelf.yaml.GenericParser;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.agentelf.yaml.TaskDescription;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * Main entry point for end to end tests fields normal use
 */
@AllArgsConstructor
public class RunTask {

    private final Map<String, TaskDescription> taskMap;
    private final TaskAndActionFactorySpring taskAndActionFactorySpring;

    public void run(Reader configYml) throws IOException {
        TaskVariables taskVariables = new GenericParser<>(TaskVariables.class)
                .parse(configYml);
        run(taskVariables);
    }

    public void run(TaskVariables taskVariables) throws IOException {
        String taskName = taskVariables.getTask();

        taskVariables.setTaskMap(taskMap);

        TaskDescription taskDescription = taskMap.get(taskName);
        if (taskDescription == null) {
            System.err.println("Task not found: " + taskName);
            System.err.println("Available tasks:");
            taskMap.keySet().stream()
                    .sorted()
                    .forEach(key -> System.err.println("  " + key));
            throw new IllegalArgumentException(
                    "Task '" + taskName + "' not found. Available tasks: "
                            + String.join(", ", taskMap.keySet()));
        }

        Task task = taskDescription.build(taskAndActionFactorySpring);
        task.execute(taskVariables);
    }

}
