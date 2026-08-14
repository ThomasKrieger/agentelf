package org.agentelf.taskandaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class TaskOrRunVariables {

    private String prompt;
    @JsonProperty("package")
    private String packageName = "";
    @JsonProperty("class")
    private String className;

}
