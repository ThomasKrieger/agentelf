package org.agentelf.taskandaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.agentelf.model.intermediate.AbstractTypeIntermediate;
import org.agentelf.model.intermediate.ModelIntermediate;
import org.agentelf.yaml.TaskDescription;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class RunVariables extends TaskOrRunVariables {

    private String llmResponse;
    private String createdClass;

    // for call task
    private Map<String, TaskDescription> taskMap;

    // For Intermediate Model processing
    private List<AbstractTypeIntermediate> intermediateTypeList;
    private ModelIntermediate modelIntermediate;

}
