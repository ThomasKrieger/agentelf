package org.agentelf.model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;

public class OWLModel {

    private static final String NS = "http://agentelf.org/generate#";

    public final Property NAME;
    public final Property DOCUMENTATION;
    public final Property DECLARATION;
    public final Property HAS_FIELD;
    public final Property HAS_METHOD;
    public final Property EXTENDS_CLASS;
    public final Property EXTENDS_INTERFACE;
    public final Property IMPLEMENTS_INTERFACE;




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

    }
}
