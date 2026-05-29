package dev.agentelf.yaml;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YamlParserTest {

    @Test
    void parseYamlInitial() {
        String yaml = """
                exampleTask:
                    description: "description"
                """;
        TaskOrActionBuilder builder = mock(TaskOrActionBuilder.class);
        TaskOrAction exampleTask = mock(TaskOrAction.class);
        when(builder.create("exampleTask")).thenReturn(exampleTask);

        YamlParser parser = new YamlParser(builder);
        parser.parse(new StringReader(yaml));

        var order = inOrder(builder,
                exampleTask);
        order.verify(builder).create("exampleTask");
        order.verify(exampleTask)
                .setProperty(eq("description"), eq("description"));
    }

    @Test
    void parseYamlWithTasks() {
        String yaml = """
                exampleTask:
                    description: "description"
                    tasks:
                      - addToPrompt:
                          text: "added value"
                      - callLLM
                      - writeToFile
                """;

        TaskOrActionBuilder builder = mock(TaskOrActionBuilder.class);

        TaskOrAction exampleTask = mock(TaskOrAction.class);
        TaskOrAction addToPrompt = mock(TaskOrAction.class);
        TaskOrAction callLLM = mock(TaskOrAction.class);
        TaskOrAction writeToFile = mock(TaskOrAction.class);

        when(builder.create("exampleTask")).thenReturn(exampleTask);
        when(builder.create("addToPrompt")).thenReturn(addToPrompt);
        when(builder.create("callLLM")).thenReturn(callLLM);
        when(builder.create("writeToFile")).thenReturn(writeToFile);

        YamlParser parser = new YamlParser(builder);

        parser.parse(new StringReader(yaml));

        var order = inOrder(builder,
                exampleTask,
                addToPrompt,
                callLLM,
                writeToFile);

        order.verify(builder).create("exampleTask");

        order.verify(exampleTask)
                .setProperty(eq("description"), eq("description"));

        order.verify(builder).create("addToPrompt");
        order.verify(exampleTask).addTask(addToPrompt);

        order.verify(addToPrompt)
                .setProperty(eq("text"), eq("added value"));

        order.verify(builder).create("callLLM");
        order.verify(exampleTask).addTask(callLLM);

        order.verify(builder).create("writeToFile");
        order.verify(exampleTask).addTask(writeToFile);
    }
}
