package org.agentelf.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class OWLModelTest {

    @Test
    public void createInterface() throws IOException {
        try(InputStream stream = this.getClass()
                .getResourceAsStream("/org/agentelf/initialresource/metamodel/generate.ttl")) {
            var model = new OWLModelBuilder().load(stream).build();
            model.createJavaInterface("FirstInterface");
            model.internalModel().write(new OutputStreamWriter(System.out), "TURTLE");


        }

    }

}
