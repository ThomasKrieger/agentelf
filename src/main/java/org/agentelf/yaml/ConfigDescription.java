package org.agentelf.yaml;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConfigDescription {

    private String task;
    private String prompt;
    @JsonProperty("package")
    private String packageName = "";
    @JsonProperty("class")
    private String className;
    private List<String> uses = new ArrayList<>();

}
