package org.agentelf.yaml;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConfigDescription {

    private String task;
    private String prompt;
    @JsonProperty("package")
    private String packageName = "";
    @JsonProperty("class")
    private String className;

}
