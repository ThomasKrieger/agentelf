package dev.agentelf.yaml;

import dev.agentelf.task.TaskBuilderForDynamic;
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

    }


    @Test
    void parseYamlMultipleMapping() {
        String yaml = """
                exampleTask:
                    description: "description"
                    elem:
                        description: "description"
                """;
        YamlParser parser = new YamlParser(null);
        parser.parse(new StringReader(yaml));

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

        TaskBuilderForDynamic taskBuilderForDynamic = new TaskBuilderForDynamic();
        YamlParser parser = new YamlParser(taskBuilderForDynamic);
        parser.parse(new StringReader(yaml));

        System.out.println(taskBuilderForDynamic.get("exampleTask"));

    }
}
