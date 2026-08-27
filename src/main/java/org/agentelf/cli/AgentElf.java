package org.agentelf.cli;

import ch.qos.logback.classic.util.ContextInitializer;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AgentElf {

    public static void main(String[] commandLine) throws IOException {
        // must be set before the first call to  LoggerFactory.getLogger();
        // ContextInitializer.CONFIG_FILE_PROPERTY is set to "agentelf/logback.xml"
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, ".agentelf/logback.xml");
        new AgentElf().run(commandLine);
    }

    public void run(String[] commandLine) throws IOException {
        new Initialize().initialize();
        String taskNameOrFile = commandLine[0];
        new RunTask(new LoadTasks().getYamlFilesFromConfigDir(),
                    new TaskAndActionFactorySpring(new AnnotationConfigApplicationContext(AppConfig.class)))
                .run(Files.newBufferedReader(Paths.get(taskNameOrFile)));
    }

}
