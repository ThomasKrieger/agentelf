package dev.agentelf.yaml;


import org.junit.jupiter.api.Test;

import java.io.StringReader;

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

        YamlParser parser = new YamlParser();

        // when
        TaskDescription root = parser.parse(new StringReader(yaml));

        assertEquals("create" , root.getName());

        TaskDescription read = root.getTasks().get(0);
        assertEquals("read", read.getName());
        assertTrue(read.getTasks().isEmpty());

        TaskDescription write = root.getTasks().get(1);
        assertEquals("write", write.getName());

        assertNotNull(write.getTasks());
        assertEquals(1, write.getTasks().size());

        TaskDescription validate = write.getTasks().get(0);
        assertEquals("validate", validate.getName());
        assertTrue(validate.getTasks().isEmpty());

    }
}
