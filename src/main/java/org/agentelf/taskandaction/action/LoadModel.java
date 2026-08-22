package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.elf.ElfModel;
import org.agentelf.yaml.GenericParser;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class LoadModel {

    @Action(arguments = {"modelPath"},
            returnVariable = "model")
    public ElfModel loadModel(String modelPath) throws IOException {
        try(Reader reader = new FileReader(modelPath)) {
            return new GenericParser<>(ElfModel.class).parse(reader);
        }
    }

}