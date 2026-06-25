package org.agentelf.model.generate;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

import java.util.*;
import java.util.function.BiConsumer;

public class OWLToJavaMapper {

    public Collection<JavaType> map(Model model) {
        Map<Resource,JavaType> result = new HashMap<>();

        ResIterator types = model.listResourcesWithProperty(RDF.type,
                model.getResource(GenerateVocab.JAVA_TYPE));

        while (types.hasNext()) {
            Resource r = types.nextResource();
            result.put(r, mapJavaType(model, r));
        }

        addLinks(model,result,GenerateVocab.EXTENDS, JavaType::addExtends);
        addLinks(model,result,GenerateVocab.USES, JavaType::addUses);

        return result.values();
    }

    private void addLinks(Model model, Map<Resource,JavaType> result,
                          String property,
                          BiConsumer<JavaType,ExternalOrJavaType> addLink) {
        for(Map.Entry<Resource,JavaType> entry : result.entrySet()) {
            // Extends
            StmtIterator ext = entry.getKey().listProperties(model.getProperty(property));
            while (ext.hasNext()) {
                Resource er = ext.nextStatement().getResource();
                if(er.hasProperty(RDF.type, model.createResource(GenerateVocab.JAVA_TYPE))) {
                    addLink.accept(entry.getValue(), result.get(er));
                } else {
                    addLink.accept(entry.getValue(),mapExternalType(model, er));
                }
            }
        }
    }

    private JavaType mapJavaType(Model model, Resource r) {
        JavaType type = new JavaType();

        type.setName(getString(model, r, GenerateVocab.NAME));
        type.setDocumentation(getString(model, r, GenerateVocab.DOCUMENTATION));
        type.setDeclaration(getString(model, r, GenerateVocab.DECLARATION));
        type.setType(getString(model, r, GenerateVocab.TYPE));

        // Fields
        StmtIterator fields = r.listProperties(model.getProperty(GenerateVocab.HAS_FIELD));
        while (fields.hasNext()) {
            Resource fr = fields.nextStatement().getResource();
            type.addField(mapField(model, fr));
        }

        // Methods
        StmtIterator methods = r.listProperties(model.getProperty(GenerateVocab.HAS_METHOD));
        while (methods.hasNext()) {
            Resource mr = methods.nextStatement().getResource();
            type.addMethod(mapMethod(model, mr));
        }
        return type;
    }

    private JavaField mapField(Model model, Resource r) {
        JavaField f = new JavaField();
        f.setDeclaration(getString(model, r, GenerateVocab.DECLARATION));
        return f;
    }

    private JavaMethod mapMethod(Model model, Resource r) {
        JavaMethod m = new JavaMethod();
        m.setDeclaration(getString(model, r, GenerateVocab.DECLARATION));
        return m;
    }

    private ExternalType mapExternalType(Model model, Resource r) {
        ExternalType ext = new ExternalType();
        ext.setDeclaration(getString(model, r, GenerateVocab.DECLARATION));
        return ext;
    }

    private String getString(Model model, Resource r, String propertyUri) {
        Statement s = r.getProperty(model.getProperty(propertyUri));
        return s != null ? s.getString() : null;
    }
}