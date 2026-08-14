package org.agentelf.endToEnd;

import org.agentelf.cli.LoadTasks;
import org.agentelf.cli.RunTask;
import org.agentelf.file.FileOutput;
import org.agentelf.llm.CallLLMList;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(EndToEndTestConfig.class)
public class CreateClassTest {

    @Autowired
    private ApplicationContext applicationContext;

    @MockitoBean
    CallLLMList callLLMList;

    @MockitoBean
    FileOutput fileOutput;

   // @Test
    public void createClass() throws IOException {
        when(callLLMList.callLarge(anyString())).thenReturn(
"""
public class TestClass {
    public static void main(String[] args) {
        System.out.println("TestClass initialized successfully.");
    }
}
""");


        String yaml =
"""
task:    createClass
prompt:  "implement TestClass"
class:   TestClass
package: org.agentelf
""";

        new RunTask( new LoadTasks().getYamlFilesFromClassPath(),
               new TaskAndActionFactorySpring(applicationContext)).run(new StringReader(yaml));

        verify(callLLMList).callLarge(
"""
implement TestClass
Output exactly one compilable Java class and nothing else.

""");
        verify(fileOutput).writeFile(
"""
public class TestClass {
    public static void main(String[] args) {
        System.out.println("TestClass initialized successfully.");
    }
}
"""
                ,  "TestClass.java" , Path.of("src/main/java/org/agentelf") );
    }




}
