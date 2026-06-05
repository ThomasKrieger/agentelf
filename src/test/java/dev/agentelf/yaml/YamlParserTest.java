package dev.agentelf.yaml;


import dev.agentelf.task.Task;
import dev.agentelf.task.TaskDynamic;
import dev.agentelf.task.TaskFactoryForDynamic;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class YamlParserTest {

    @Test
    void parsesYamlAndBuildsTaskTree() {

        // given
        String yaml =
                """
                name: create
                tasks:
                  - name: read
                  - name: write
                    tasks:
                      - name: validate
                """;

        TaskFactoryForDynamic factory = new TaskFactoryForDynamic();
        YamlParser parser = new YamlParser(factory);

        // when
        Task root = parser.parse(new StringReader(yaml));

        // then
        assertNotNull(root);
        assertTrue(root instanceof TaskDynamic);

        TaskDynamic rootDynamic = factory.get("create");
        assertNotNull(rootDynamic);

        // root properties
        assertEquals("create", rootDynamic.getName());

        // children should also be created via factory
        TaskDynamic read = factory.get("read");
        TaskDynamic write = factory.get("write");
        TaskDynamic validate = factory.get("validate");

        assertNotNull(read);
        assertNotNull(write);
        assertNotNull(validate);

        Set<String> actual = rootDynamic.getTasks().stream()
                .map((task) -> ((TaskDynamic) task).getName())
                .collect(Collectors.toSet());

        Set<String> expected = Set.of("read", "write");

        assertEquals(expected, actual);


    }
}
