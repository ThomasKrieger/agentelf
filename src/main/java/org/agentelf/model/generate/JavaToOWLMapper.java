package org.agentelf.model.generate;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class JavaToOWLMapper {

    private final Model model;
    private final String ns;

    public JavaToOWLMapper(Model model, String namespace) {
        this.model = model;
        this.ns = namespace;
    }

    public Collection<Resource> mapJavaTypes(List<JavaType> types) {
        Map<JavaType,Resource> result = new HashMap<>();
        for (JavaType type : types) {
            result.put(type,mapJavaType(type));
        }

        addLinks(result,GenerateVocab.EXTENDS, JavaType::getExtendsTypes);
        addLinks(result,GenerateVocab.USES, JavaType::getUses);

        return result.values();
    }

    private void addLinks(Map<JavaType,Resource> result,
                          String property,
                          Function<JavaType,List<ExternalOrJavaType>> getLinkedTypes) {
        for(Map.Entry<JavaType,Resource> entry : result.entrySet()) {
            for (ExternalOrJavaType ext : getLinkedTypes.apply(entry.getKey())) {
                Object obj = ext.onExternalClass(this::mapExternalType);
                if(obj != null) {
                    entry.getValue().addProperty(model.createProperty(property), (Resource) obj);
                } else {
                    entry.getValue().addProperty(model.createProperty(property), result.get((JavaType)ext));
                }
            }
        }
    }

    private Resource mapJavaType(JavaType type) {
        Resource r = model.createResource(ns + safe(type.getName()));
        // RDF type
        r.addProperty(RDF.type, model.createResource(GenerateVocab.JAVA_TYPE));
        // basic properties
        if (type.getName() != null) {
            r.addProperty(model.createProperty(GenerateVocab.NAME), type.getName());
        }
        if (type.getDocumentation() != null) {
            r.addProperty(model.createProperty(GenerateVocab.DOCUMENTATION),
                    type.getDocumentation());
        }
        if (type.getDeclaration() != null) {
            r.addProperty(model.createProperty(GenerateVocab.DECLARATION),
                    type.getDeclaration());
        }
        if (type.getType() != null) {
            r.addProperty(model.createProperty(GenerateVocab.TYPE),
                    type.getType());
        }
        // fields
        for (JavaField f : type.getFields()) {
            Resource fr = mapField(f);
            r.addProperty(model.createProperty(GenerateVocab.HAS_FIELD), fr);
        }
        // methods
        for (JavaMethod m : type.getMethods()) {
            Resource mr = mapMethod(m);
            r.addProperty(model.createProperty(GenerateVocab.HAS_METHOD), mr);
        }
        return r;
    }

    private Resource mapField(JavaField field) {

        Resource r = model.createResource();
        r.addProperty(RDF.type, model.createResource(GenerateVocab.JAVA_FIELD));
        if (field.getDeclaration() != null) {
            r.addProperty(model.createProperty(GenerateVocab.DECLARATION),
                    field.getDeclaration());
        }
        return r;
    }

    private Resource mapMethod(JavaMethod method) {

        Resource r = model.createResource();
        r.addProperty(RDF.type, model.createResource(GenerateVocab.JAVA_METHOD));
        if (method.getDeclaration() != null) {
            r.addProperty(model.createProperty(GenerateVocab.DECLARATION),
                    method.getDeclaration());
        }
        return r;
    }

    private Resource mapExternalType(ExternalType ext) {

        Resource r = model.createResource();
        r.addProperty(RDF.type, model.createResource(GenerateVocab.EXTERNAL_TYPE));
        if (ext.getDeclaration() != null) {
            r.addProperty(model.createProperty(GenerateVocab.DECLARATION),
                    ext.getDeclaration());
        }
        return r;
    }

    private String safe(String name) {
        if (name == null) return "unknown";
        return name.replaceAll("[^a-zA-Z0-9_]", "_");
    }

}