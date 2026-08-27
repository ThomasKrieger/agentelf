package org.agentelf.taskandaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.agentelf.model.source.SourceModel;
import org.agentelf.model.type.TypeModel;

@EqualsAndHashCode(callSuper = true)
@Data
public class RunVariables extends TaskOrRunVariables {

    private String llmResponse;
    private String createdClass;

    private TypeModel model;

    // For Intermediate Model processing
    private SourceModel sourceModel;

}
