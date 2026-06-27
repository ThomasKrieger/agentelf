package org.agentelf.model.uml.description;

import lombok.Data;

import java.util.List;

@Data
public class UMLDescription {

    private List<UMLClass> classes;
    private List<UMLClass> interfaces;


}
