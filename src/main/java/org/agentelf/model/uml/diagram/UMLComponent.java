package org.agentelf.model.uml.diagram;

import lombok.Data;

import java.util.List;

@Data
public class UMLComponent {

    private String name;
    private String type;
    private List<UMLLink> outgoing;

}
