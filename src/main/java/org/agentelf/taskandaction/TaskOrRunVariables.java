package org.agentelf.taskandaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.agentelf.model.tosource.AbstractTypeToSource;
import org.agentelf.yaml.TaskDescription;

import java.util.List;
import java.util.Map;

@Data
public abstract class TaskOrRunVariables {

    private String prompt;
    @JsonProperty("package")
    private String packageName = "";
    @JsonProperty("class")
    private String className;

    // see https://github.com/resilience4j/resilience4j/issues/1966
    @JsonProperty("model-path")
    private String modelPath;

    // for call task
    private Map<String, TaskDescription> taskMap;

    // For Intermediate Model processing
    private List<AbstractTypeToSource> intermediateTypeList;

}
