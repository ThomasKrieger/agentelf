package org.agentelf.taskandaction;

import lombok.Data;

@Data
public class RunVariables {

    private String prompt;
    private String llmResponse;
    private String packageName;
    private String className;

}
