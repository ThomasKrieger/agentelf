package org.agentelf.yaml;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class TaskParserTest {

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

        TaskParser parser = new TaskParser();

        // when
        TaskDescription root = parser.parse(new StringReader(yaml));

        assertEquals("create" , root.getName());

        ActionDescription read = root.getActions().get(0);
        assertEquals("read", read.getName());


        ActionDescription write = root.getActions().get(1);
        assertEquals("write", write.getName());


    }
}
