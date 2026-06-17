package org.agentelf.api;

import org.agentelf.file.FileOutput;
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
    private String firstParameter;
    private String secondParameter;


}
