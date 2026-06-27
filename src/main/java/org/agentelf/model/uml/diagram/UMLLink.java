package org.agentelf.model.uml.diagram;

import lombok.Data;

@Data
public class UMLLink {

    private UMLComponent incoming;
    private UMLComponent outgoing;
    private String linkType;

}
