package org.agentelf.model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;

import java.util.ArrayList;
import java.util.List;

public class OWLModel {

    private static final String NS = "http://agentelf.org/generate#";
    private static final String JAVA_INTERFACE_LABEL = "JavaInterface";

    public final Property NAME;
    public final Property DOCUMENTATION;
    public final Property DECLARATION;
    public final Property HAS_FIELD;
    public final Property HAS_METHOD;
    public final Property EXTENDS_CLASS;
    public final Property EXTENDS_INTERFACE;
    public final Property IMPLEMENTS_INTERFACE;

    public final Resource JAVA_INTERFACE;

    private final Model model;

    public OWLModel(Model model) {
        this.model = model;
        this.NAME = model.createProperty(NS, "name");
        this.DOCUMENTATION = model.createProperty(NS, "documentation");
        this.DECLARATION = model.createProperty(NS, "declaration");
        this.HAS_FIELD = model.createProperty(NS, "hasField");
        this.HAS_METHOD = model.createProperty(NS, "hasMethod");
        this.EXTENDS_CLASS = model.createProperty(NS, "extendsClass");
        this.EXTENDS_INTERFACE = model.createProperty(NS, "extendsInterface");
        this.IMPLEMENTS_INTERFACE = model.createProperty(NS, "implementsInterface");

        this.JAVA_INTERFACE = model.createResource(NS + JAVA_INTERFACE_LABEL);
    }

    public Resource createJavaInterface(String name) {
        Resource result =  model.createResource(NS + JAVA_INTERFACE_LABEL + "/" + name);
        result.addProperty(RDF.type,JAVA_INTERFACE);
        result.addProperty(NAME,name);
        return result;
    }

    public List<Resource> getResourceList(Resource typ) {
        List<Resource> result = new ArrayList<>();
        ResIterator iterator = model.listSubjectsWithProperty(RDF.type, typ);
        while(iterator.hasNext()) {
            result.add(iterator.nextResource());
        }
        return result;
    }

    public Model internalModel() {
        return model;
    }

}
