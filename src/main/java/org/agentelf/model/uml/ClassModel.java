package org.agentelf.model.uml;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class ClassModel {

    private final String name;
    private final String documentation;
    private final List<MethodModel> methods = new LinkedList<>();
    private final List<VariabelModel> fields = new LinkedList<>();

    public ClassModel(String name, String documentation) {
        this.name = name;
        this.documentation = documentation;
    }


}
