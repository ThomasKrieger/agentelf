package org.agentelf.yaml;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class YamlParserTest {

    @Test
    void parsesYamlAndBuildsTaskTree() throws IOException {

        // given
        String yaml =
                """
                name: create
                actions:
                  - name: read
                  - name: write
                """;

        YamlParser parser = new YamlParser();

        // when
        TaskDescription root = parser.parse(new StringReader(yaml));

        assertEquals("create" , root.getName());

        ActionDescription read = root.getActions().get(0);
        assertEquals("read", read.getName());


        ActionDescription write = root.getActions().get(1);
        assertEquals("write", write.getName());


    }
}
