package org.agentelf.model.umlclass;

import lombok.Getter;

@Getter
public class UMLFiled {

    private final String name;
    private final String variableType;

    public UMLFiled(String name, String variableType) {
        this.name = name;
        this.variableType = variableType;
    }

}
