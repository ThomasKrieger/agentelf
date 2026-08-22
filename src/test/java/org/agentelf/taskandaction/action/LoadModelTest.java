package org.agentelf.taskandaction.action;

import org.agentelf.model.elf.ElfModel;
import org.agentelf.model.unittest.UnitTestLLM;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class LoadModelTest {

    @TempDir
    private Path tempDir;

    @Test
    public void testLoadModelImmutable() throws IOException {
        Path modelFile = tempDir.resolve("model.yaml");
        String yamlContent = """
                package: "org.agentelf.test"
                records:
                  - name: ImmutableClassOne
                    fields:
                      - "id"
                      - "name"
                    methods:
                      - documentation: "Get the identifier"
                        prompt: "What is the ID?"
                        declaration: "public String getId()"
                        label: "getId"
                    implements:
                      - TaskParser
                  - name: ImmutableClassTwo
                """;
        
        Files.writeString(modelFile, yamlContent);

        LoadModel loader = new LoadModel();
        ElfModel model = loader.loadModel(modelFile.toAbsolutePath().toString());

        assertNotNull(model, "Parsed model should not be null");
        assertEquals("org.agentelf.test", model.packageName());
        assertEquals(2, model.records().size());
        
        var immutable = model.records().get(0);
        assertEquals(2, immutable.fields().size());
        assertEquals("id", immutable.fields().get(0));
        
        assertEquals(1, immutable.methods().size());
        assertEquals("getId", immutable.methods().get(0).label());
        assertEquals("Get the identifier", immutable.methods().get(0).documentation());
        
        assertEquals(1, immutable.implementsInterfaces().size());
    }

    @Test
    public void testLoadModelImmutableWithUnitTestPrompt() throws IOException {
        Path modelFile = tempDir.resolve("model.yaml");
        String yamlContent = """
                package: "org.agentelf.test"
                records:
                  - name: ImmutableClassOne
                    unitTest:
                        type: llm
                        prompt: "test multiple classes"
                """;

        Files.writeString(modelFile, yamlContent);

        LoadModel loader = new LoadModel();
        ElfModel model = loader.loadModel(modelFile.toAbsolutePath().toString());

        assertNotNull(model, "Parsed model should not be null");
        assertEquals("org.agentelf.test", model.packageName());
        assertEquals(1, model.records().size());

        var immutable = model.records().get(0);
        assertInstanceOf(UnitTestLLM.class, immutable.unitTest());
        UnitTestLLM unitTestLLM = (UnitTestLLM) immutable.unitTest();
        assertEquals("test multiple classes", unitTestLLM.getPrompt());

    }

    @Test
    public void testLoadModelFileNotFound() {
        LoadModel loader = new LoadModel();
        assertThrows(IOException.class, () -> {
            loader.loadModel("non_existent_file.yaml");
        });
    }
}