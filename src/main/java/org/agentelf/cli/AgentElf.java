package org.agentelf.cli;

import org.agentelf.task.Task;
import org.agentelf.task.TaskVariables;
import org.agentelf.yaml.ConfigDescription;
import org.agentelf.yaml.ConfigParser;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.agentelf.yaml.TaskDescription;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class AgentElf {

    public static void main(String[] commandLine) throws IOException {
        new AgentElf().run(commandLine);
    }

    public void run(String[] commandLine) throws IOException {
        new Initialize().initialize();
        String taskNameOrFile = commandLine[0];
        TaskVariables taskVariables = new TaskVariables();
        ConfigDescription config = new ConfigParser().parse(Files.newBufferedReader(Paths.get(taskNameOrFile)));
        taskVariables.setParameter(config.getPrompt());
        taskVariables.setPackageName(config.getPackageName());
        taskVariables.setClassName(config.getClassName());
        String taskName = config.getTask();

        Map<String, TaskDescription> taskMap = new LoadTasks().loadTasks();

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


        System.out.println(taskVariables.getParameter());

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        TaskAndActionFactorySpring taskAndActionFactorySpring = new TaskAndActionFactorySpring(ctx);
        Task task = taskDescription.build(taskAndActionFactorySpring);
        task.execute(taskVariables);
    }

}
