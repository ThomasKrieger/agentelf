package org.agentelf.model.uml.diagram;

import lombok.Data;

import java.util.List;

@Data
public class UMLDiagram {

    private String type;
    private List<UMLComponent> components;

}
