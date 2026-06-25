package org.agentelf.api;

import org.agentelf.file.FileOutput;
import lombok.Data;

import java.io.File;
import java.util.List;

@Data
public class RunVariables {

    private FileOutput fileOutput = new FileOutput();

    private String prompt;
    private File generatedDir;
    private String llmResponse;
    private String packageName;
    private String className;
    private String parameter;
    private List<String> uses;

}
