package org.agentelf.cli;

import org.agentelf.taskandaction.task.Task;
import org.agentelf.taskandaction.task.TaskVariables;
import org.agentelf.yaml.GenericParser;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.agentelf.yaml.TaskDescription;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * Main entry point for end to end tests and normal use
 */
public class RunTask {

    public void run(Reader configYml,
                    Reader[] yamlFiles,
                    ApplicationContext ctx) throws IOException {


        TaskVariables taskVariables = new GenericParser<>(TaskVariables.class)
                .parse(configYml);

        String taskName = taskVariables.getTask();
        Map<String, TaskDescription> taskMap = new LoadTasks().loadTasks(yamlFiles);
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

        TaskAndActionFactorySpring taskAndActionFactorySpring = new TaskAndActionFactorySpring(ctx);
        Task task = taskDescription.build(taskAndActionFactorySpring);
        task.execute(taskVariables);
    }

}
