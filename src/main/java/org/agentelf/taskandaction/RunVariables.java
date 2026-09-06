package org.agentelf.taskandaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.SourceModel;
import org.agentelf.model.type.TypeModel;
import org.agentelf.type.ReferenceTypeRepo;

@EqualsAndHashCode(callSuper = true)
@Data
public class RunVariables extends TaskOrRunVariables {

    private String llmResponse;
    private String createdClass;

    private TypeModel model;
    private AbstractTypeSource currentType;

    // For Intermediate Model processing
    private ReferenceTypeRepo referenceTypeRepo;
    private SourceModel sourceModel;

}
