package org.agentelf.cli;

import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AgentElf {

    public static void main(String[] commandLine) throws IOException {
       new AgentElf().run(commandLine);
        //IntermediateToSource.run();
    }

    public void run(String[] commandLine) throws IOException {
        new Initialize().initialize();
        String taskNameOrFile = commandLine[0];
        new RunTask(new LoadTasks().getYamlFilesFromConfigDir(),
                    new TaskAndActionFactorySpring(new AnnotationConfigApplicationContext(AppConfig.class)))
                .run(Files.newBufferedReader(Paths.get(taskNameOrFile)));
    }

}
