package org.agentelf.model.generate;

import lombok.Getter;
import org.agentelf.model.OWLModel;
import org.apache.jena.rdf.model.Resource;

import java.util.ArrayList;
import java.util.List;

@Getter
public class JavaClass {

    private String name;
    private String documentation;
    private JavaClass superClass;

    private final List<JavaClass> extendsClass = new ArrayList<>();
    private final List<JavaInterface> implementsInterface = new ArrayList<>();

    private final List<JavaField> fields = new ArrayList<>();
    private final List<JavaMethod> methods = new ArrayList<>();

    public static JavaClass fromResource(Resource resource,OWLModel model) {
        return null;
    }

    public Resource toResource(OWLModel model) {
        return null;
    }

}
