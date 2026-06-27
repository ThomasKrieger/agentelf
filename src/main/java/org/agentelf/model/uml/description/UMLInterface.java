package org.agentelf.model.uml.description;

import lombok.Data;

import java.util.List;

@Data
public class UMLInterface {

    private String name;
    private List<String> methods;

}
