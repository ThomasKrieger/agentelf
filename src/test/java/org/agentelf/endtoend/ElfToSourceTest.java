package org.agentelf.endtoend;

import org.agentelf.cli.LoadTasks;
import org.agentelf.cli.RunTask;
import org.agentelf.yaml.TaskAndActionFactorySpring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.agentelf.util.ResourceReader.asString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ElfToSourceTest extends AbstractEndToEndTest {

    @TempDir
    private Path tempDir;

    @Value("classpath:/endtoend/TestClass.java")
    private Resource testClassJava;

    @Test
    void functionalToSource() throws IOException {
        Path modelFile = tempDir.resolve("model.yaml");
        String yamlContent = """
                package: "org.agentelf.test"
                classes:
                  - name: ImmutableClassOne
                    fields:
                      - "int id;"
                      - "String name;"
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

        String yamlCommand = "task: functionalToSource" +
                System.lineSeparator() +
                "model-path: \"" + modelFile.toAbsolutePath() + '"';

        new RunTask( new LoadTasks().getYamlFilesFromClassPath(),
                new TaskAndActionFactorySpring(applicationContext)).run(new StringReader(yamlCommand));

        verify(callLLMList,times(2)).callLarge(promptCaptor.capture());
        verify(fileOutput,times(2)).writeFile(contentCaptor.capture(),
                fileNameCaptor.capture() ,
                pathCaptor.capture());

        System.out.println(promptCaptor.getAllValues());
    }

}
