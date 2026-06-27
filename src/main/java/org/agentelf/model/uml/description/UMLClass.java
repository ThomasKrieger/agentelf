package org.agentelf.model.uml.description;

import lombok.Data;

import java.util.List;

@Data
public class UMLClass {

    private String name;
    private List<String> fields;
    private List<String> methods;

}
