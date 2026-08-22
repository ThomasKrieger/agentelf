package org.agentelf.taskandaction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.agentelf.model.elf.ElfModel;
import org.agentelf.model.intermediate.ModelIntermediate;

@EqualsAndHashCode(callSuper = true)
@Data
public class RunVariables extends TaskOrRunVariables {

    private String llmResponse;
    private String createdClass;

    private ElfModel model;

    // For Intermediate Model processing
    private ModelIntermediate modelIntermediate;

}
