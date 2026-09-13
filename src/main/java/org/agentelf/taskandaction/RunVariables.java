package org.agentelf.taskandaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.agentelf.model.tosource.AbstractTypeToSource;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.model.type.TypeModel;
import org.agentelf.type.ReferenceTypeRepo;

@EqualsAndHashCode(callSuper = true)
@Data
public class RunVariables extends TaskOrRunVariables {

    private String llmResponse;
    private String createdClass;

    private TypeModel model;
    private AbstractTypeToSource currentType;

    // For Intermediate Model processing
    private ReferenceTypeRepo referenceTypeRepo;
    private ToSourceModel toSourceModel;

}
