package org.agentelf.endtoend;


import org.agentelf.cli.LoadTasks;
import org.agentelf.cli.RunTask;
import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.ClassSource;
import org.agentelf.model.source.SourceTypeListBuilder;
import org.agentelf.model.source.TypeDescriptionSource;
import org.agentelf.taskandaction.task.TaskVariables;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

import static org.agentelf.util.DiffText.assertTextEquals;
import static org.agentelf.util.ResourceReader.asString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SourceModelToSourceTest extends AbstractEndToEndTest{

    @Value("classpath:/endtoend/TestClass.java")
    private Resource testClassJava;

    @Test
    public void oneClass() throws IOException {
        when(callLLMList.callLarge(anyString())).thenReturn(asString(testClassJava));

        ArgumentCaptor<String> promptCaptor =
                ArgumentCaptor.forClass(String.class);

        SourceTypeListBuilder builder = new SourceTypeListBuilder();

        ClassSource parseMethodClass = builder.addClass("org.agentelf.javaparser", "ParseMethod");
        parseMethodClass.addMethodWithDocumentation(TypeDescriptionSource.create("org.agentelf.model.intermediate.MethodIntermediate") , "parseMethod" , "Uses StaticJavaParser parseBodyDeclaration to parse a string" +
                "containing a java method and returns a MethodIntermediate");

        List<AbstractTypeSource> list = builder.build();

        TaskVariables taskVariables = new TaskVariables();
        taskVariables.setTask("sourceModelToSource");
        taskVariables.setIntermediateTypeList(list);

        new RunTask(new LoadTasks().getYamlFilesFromClassPath(),
                new TaskAndActionFactorySpring(applicationContext))
                .run(taskVariables);

        verify(callLLMList,times(2)).callLarge(promptCaptor.capture());
        assertTextEquals("/endtoend/sourcemodeltosource/oneClassCreateClassPrompt.txt",promptCaptor.getAllValues().get(0));
        assertTextEquals("/endtoend/sourcemodeltosource/oneClassCreateTestPrompt.txt",promptCaptor.getAllValues().get(1));
    }

}
