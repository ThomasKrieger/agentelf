package org.agentelf.taskandaction;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RunVariables extends TaskOrRunVariables {

    private String llmResponse;

}
