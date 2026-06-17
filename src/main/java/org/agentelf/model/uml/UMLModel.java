package org.agentelf.model.uml;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class UMLModel {

    private final List<ClassModel> classes = new LinkedList<>();

}
