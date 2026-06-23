package org.agentelf.model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class OWLModelBuilder {

    private final Model model;

    public OWLModelBuilder() {
        this.model = ModelFactory.createDefaultModel();
    }

    public OWLModelBuilder load(Path turtleFile) throws IOException {
        try (InputStream in = Files.newInputStream(turtleFile)) {
            RDFDataMgr.read(model, in, Lang.TURTLE);
        }
        return this;
    }

    public OWLModelBuilder load(InputStream stream) throws IOException {
        RDFDataMgr.read(model, stream, Lang.TURTLE);
        return this;
    }

    public OWLModel build() {
        return new OWLModel(model);
    }



}
