package org.agentelf.model.uml;

import lombok.Getter;

@Getter
public class VariabelModel {

    private final String name;
    private final String variableType;

    public VariabelModel(String name, String variableType) {
        this.name = name;
        this.variableType = variableType;
    }




}
