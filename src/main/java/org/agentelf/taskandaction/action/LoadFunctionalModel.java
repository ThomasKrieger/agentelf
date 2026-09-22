package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.functional.FunctionalModel;
import org.agentelf.yaml.GenericParser;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class LoadFunctionalModel {

    @Action(arguments = {"modelPath"},
            returnVariable = "model")
    public FunctionalModel loadFunctionalModel(String modelPath) throws IOException {
        try(Reader reader = new FileReader(modelPath)) {
            return new GenericParser<>(FunctionalModel.class).parse(reader);
        }
    }

}