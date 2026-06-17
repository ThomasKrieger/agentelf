package org.agentelf.cli;

import com.beust.jcommander.JCommander;
import org.agentelf.task.Task;
import org.agentelf.task.TaskVariables;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.agentelf.yaml.TaskDescription;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Map;

public class AgentElf {

    public static void main(String[] commandLine) throws IOException {
        new Initialize().initialize();

        var arguments = new Arguments();
        JCommander commandLineParser = JCommander.newBuilder()
                .addObject(arguments)
                .build();
        commandLineParser.parse(commandLine);
        if(arguments.getCommandAndPrompts().isEmpty()) {
            commandLineParser.usage();
            return;
        }

        Map<String, TaskDescription> taskMap = new LoadTasks().loadTasks();
        TaskDescription taskDescription = taskMap.get(arguments.getCommandAndPrompts().getFirst());

        if (taskDescription == null) {
            System.err.println("Task not found: " + taskDescription);
            System.err.println("Available tasks:");
            taskMap.keySet().stream()
                    .sorted()
                    .forEach(key -> System.err.println("  " + key));
            throw new IllegalArgumentException(
                    "Task '" + taskDescription + "' not found. Available tasks: "
                            + String.join(", ", taskMap.keySet()));
        }

        TaskVariables taskVariables = new TaskVariables();
        if( arguments.getCommandAndPrompts().size() == 2) {
            taskVariables.setFirstParameter(arguments.getCommandAndPrompts().get(1));
        }
        if( arguments.getCommandAndPrompts().size() == 3) {
            taskVariables.setSecondParameter(arguments.getCommandAndPrompts().get(2));
        }

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        TaskAndActionFactorySpring taskAndActionFactorySpring = new TaskAndActionFactorySpring(ctx);
        Task task = taskDescription.build(taskAndActionFactorySpring);
        task.execute(taskVariables);
    }


}
