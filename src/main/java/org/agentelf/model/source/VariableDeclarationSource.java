package org.agentelf.model.source;

import lombok.Data;

@Data
public class VariableDeclarationSource {

    private TypeDescriptionSource type;
    private String name;

}
