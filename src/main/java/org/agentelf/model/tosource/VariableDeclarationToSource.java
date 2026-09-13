package org.agentelf.model.tosource;

import lombok.Data;

@Data
public class VariableDeclarationToSource {

    private TypeDescriptionToSource type;
    private String name;

}
