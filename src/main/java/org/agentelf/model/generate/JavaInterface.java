package org.agentelf.model.generate;

import lombok.Data;
import org.agentelf.model.OWLModel;
import org.apache.jena.rdf.model.Resource;

import java.util.ArrayList;
import java.util.List;

@Data
public class JavaInterface {

    private String name;
    private String documentation;
    private final List<JavaMethod> methods = new ArrayList<>();
    private final List<JavaInterface> extendsInterface = new ArrayList<>();

    public static JavaInterface fromResource(Resource resource, OWLModel model) {
        JavaInterface javaInterface = new JavaInterface();
        if (resource.hasProperty(model.NAME)) {
            javaInterface.setName(resource.getProperty(model.NAME).getString());
        }
        if (resource.hasProperty(model.DOCUMENTATION)) {
            javaInterface.setDocumentation(resource.getProperty(model.DOCUMENTATION).getString());
        }
        return javaInterface;
    }

    public Resource toResource(OWLModel model) {

        Resource resource = model.createJavaInterface(name);
        if (name != null) {
            resource.addProperty(model.NAME, name);
        }
        if (documentation != null) {
            resource.addProperty(model.DOCUMENTATION, documentation);
        }

        for (JavaMethod method : methods) {
            resource.addProperty(model.HAS_METHOD, model.internalModel().createResource());
        }

        for (JavaInterface intf : extendsInterface) {
            resource.addProperty(model.EXTENDS_INTERFACE, model.internalModel().createResource());
        }

        return resource;
    }

}
