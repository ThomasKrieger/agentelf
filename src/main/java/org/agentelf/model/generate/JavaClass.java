package org.agentelf.model.generate;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class JavaClass {

    private String name;
    private String documentation;
    private JavaClass superClass;

    private final List<JavaField> fields = new ArrayList<>();
    private final List<JavaMethod> methods = new ArrayList<>();

    private final List<JavaInterface> implementedInterfaces = new ArrayList<>();

}
