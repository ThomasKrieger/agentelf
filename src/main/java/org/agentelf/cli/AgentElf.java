package org.agentelf.cli;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AgentElf {

    public static void main(String[] commandLine) throws IOException {
        new AgentElf().run(commandLine);
    }

    public void run(String[] commandLine) throws IOException {
        new Initialize().initialize();
        String taskNameOrFile = commandLine[0];
        new RunTask().run(Files.newBufferedReader(Paths.get(taskNameOrFile)),
                new LoadTasks().getYamlFilesFromConfigDir(),
                new AnnotationConfigApplicationContext(AppConfig.class));
    }

}
