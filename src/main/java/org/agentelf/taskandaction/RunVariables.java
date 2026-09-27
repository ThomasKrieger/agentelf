package org.agentelf.taskandaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.model.tosource.TypeToSource;
import org.agentelf.type.TypeRepo;

@EqualsAndHashCode(callSuper = true)
@Data
public class RunVariables extends TaskOrRunVariables {

    private String llmResponse;
    private String createdClass;

    private Object model;
    private TypeToSource currentType;

    private TypeRepo typeRepo;
    private ToSourceModel toSourceModel;

}
