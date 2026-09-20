package org.agentelf.endtoend;

import org.agentelf.cli.LoadTasks;
import org.agentelf.cli.RunTask;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.agentelf.util.DiffText.assertTextEquals;
import static org.agentelf.util.ResourceReader.asString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TypeToSourceTest extends AbstractEndToEndTest {

    @TempDir
    private Path tempDir;

    @Value("classpath:/endtoend/TestClass.java")
    private Resource testClassJava;

    //@Test
    void classToSource() throws IOException {
        Path modelFile = tempDir.resolve("model.yaml");
        String yamlContent = """
                package: "org.agentelf.test"
                classes:
                  - name: ImmutableClassOne
                    fields:
                      - declaration: "int id;"
                      - declaration: "String name;"
                    methods:
                      - documentation: "Get the identifier"
                        prompt: "What is the ID?"
                        declaration: "public String getId()"
                        label: "getId"
                """;
        Files.writeString(modelFile, yamlContent);

        ArgumentCaptor<String> promptCaptor =
                ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<String> contentCaptor =
                ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> fileNameCaptor =
                ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Path> pathCaptor =
                ArgumentCaptor.forClass(Path.class);

        when(callLLMList.callLarge(anyString())).thenReturn(asString(testClassJava));

        String yamlCommand = "task: typeModelToSource" +
                System.lineSeparator() +
                "model-path: \"" + modelFile.toAbsolutePath() + '"';

        new RunTask(new LoadTasks().getYamlFilesFromClassPath(),
                new TaskAndActionFactorySpring(applicationContext)).run(new StringReader(yamlCommand));

        verify(callLLMList,times(2)).callLarge(promptCaptor.capture());
        verify(fileOutput,times(2)).writeFile(contentCaptor.capture(),
                fileNameCaptor.capture(),
                pathCaptor.capture());

        assertTextEquals("/endtoend/typetosource/CreateClassPrompt.txt",promptCaptor.getAllValues().get(0));
        assertTextEquals("/endtoend/typetosource/UnitTestPrompt.txt",promptCaptor.getAllValues().get(1));

        assertEquals("ImmutableClassOne.java",  fileNameCaptor.getAllValues().get(0));
        assertEquals("ImmutableClassOneTest.java",  fileNameCaptor.getAllValues().get(1));

        assertEquals(Path.of("src/main/java/org/agentelf/test"),  pathCaptor.getAllValues().get(0));
        assertEquals(Path.of("src/test/java/org/agentelf/test"),  pathCaptor.getAllValues().get(1));
    }

}
