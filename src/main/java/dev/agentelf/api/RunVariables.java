package dev.agentelf.api;

import dev.agentelf.file.FileOutput;
import lombok.Data;

import java.io.File;

@Data
public class RunVariables {

    private FileOutput fileOutput;

    private String prompt;
    private File generatedDir;
    private String llmResponse;
    private String packageName;
    private String className;

}
