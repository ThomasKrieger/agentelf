package org.agentelf.cli;

import org.agentelf.model.intermediate.AbstractTypeIntermediate;
import org.agentelf.model.intermediate.IntermediateTypeListFactory;
import org.agentelf.taskandaction.task.TaskVariables;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class IntermediateToSource {

    public static void run() throws IOException {
        new Initialize().initialize();
        List<AbstractTypeIntermediate> list = IntermediateTypeListFactory.create();
        TaskVariables taskVariables = new TaskVariables();
        taskVariables.setTask("intermediateToSource");
        taskVariables.setIntermediateTypeList(list);
        new RunTask(new LoadTasks().getYamlFilesFromConfigDir(),
                    new TaskAndActionFactorySpring(new AnnotationConfigApplicationContext(AppConfig.class)))
                .run(taskVariables);
    }

}
