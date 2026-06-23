package org.agentelf.model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class OWLModel {

    private final Model model;

    public OWLModel() {
        this.model = ModelFactory.createDefaultModel();
    }

    public void load(Path turtleFile) throws IOException {
        try (InputStream in = Files.newInputStream(turtleFile)) {
            RDFDataMgr.read(model, in, Lang.TURTLE);
        }
    }


}
