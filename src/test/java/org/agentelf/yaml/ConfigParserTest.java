package org.agentelf.yaml;

import org.junit.jupiter.api.Test;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConfigParserTest {

    @Test
    public void testParseConfig() throws Exception {
        String yamlContent = 
            "task: createClass\n" +
            "prompt: \"write a test for ConfigParser using the given yml\"\n" +
            "class: ConfigParserTest\n" +
            "package: org.agentelf.yaml";

        ConfigParser parser = new ConfigParser();
        ConfigDescription description = parser.parse(new StringReader(yamlContent));

        assertNotNull(description);
        assertEquals("createClass", description.getTask());
        assertEquals("write a test for ConfigParser using the given yml", description.getPrompt());
        assertEquals("ConfigParserTest", description.getClassName());
        assertEquals("org.agentelf.yaml", description.getPackageName());
    }
}