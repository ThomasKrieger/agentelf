package org.agentelf.endtoend;

import org.agentelf.cli.LoadTasks;
import org.agentelf.cli.RunTask;
import org.agentelf.file.FileOutput;
import org.agentelf.llm.CallLLMList;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;

import static org.agentelf.util.DiffText.assertTextEquals;
import static org.agentelf.util.ResourceReader.asString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(EndToEndTestConfig.class)
public class CreateClassTest {

    @Autowired
    private ApplicationContext applicationContext;

    @MockitoBean
    private CallLLMList callLLMList;

    @MockitoBean
    private FileOutput fileOutput;

    @Value("classpath:/endtoend/TestClass.java")
    private Resource testClassJava;

    @Value("classpath:/endtoend/createClass.yml")
    private Resource createClassYml;


    @Test
    public void createClass() throws IOException {
        when(callLLMList.callLarge(anyString())).thenReturn(asString(testClassJava));

        ArgumentCaptor<String> promptCaptor =
                ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<String> contentCaptor =
                ArgumentCaptor.forClass(String.class);

        new RunTask( new LoadTasks().getYamlFilesFromClassPath(),
               new TaskAndActionFactorySpring(applicationContext)).run(new StringReader(asString(createClassYml)));

        verify(callLLMList).callLarge(promptCaptor.capture());
        verify(fileOutput).writeFile(contentCaptor.capture(),
                eq("TestClass.java") ,
                eq(Path.of("src/main/java/org/agentelf/taskandaction/action")));
        assertTextEquals("/endtoend/createClassPrompt.txt",promptCaptor.getValue());
        assertTextEquals("/endtoend/createClassContent.txt",contentCaptor.getValue());
    }

}
