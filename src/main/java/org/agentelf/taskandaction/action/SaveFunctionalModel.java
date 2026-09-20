package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.functional.AlgebraicDataTypeModel;
import org.agentelf.model.functional.FunctionalTypeToJavapoetType;
import org.agentelf.sourcebuilder.ReferenceTypeHandleAndSource;
import org.agentelf.sourcebuilder.SourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class SaveFunctionalModel {

    @Value("${main-target-dir}")
    private String targetDir;

    @Action(arguments = {"model"})
    public void saveFunctionalModel(AlgebraicDataTypeModel model) throws IOException {
        Path rootDir =  Path.of(targetDir);
        FunctionalTypeToJavapoetType functionalTypeToJavapoetType = new FunctionalTypeToJavapoetType();
        SourceBuilder sourceBuilder = new SourceBuilder();
        model.addToBuilder(sourceBuilder, functionalTypeToJavapoetType);
        for(ReferenceTypeHandleAndSource elem : sourceBuilder.build()) {
            Path packageDir = rootDir.resolve(elem.handle().packageName().replace('.', File.separatorChar));
            Files.createDirectories(packageDir);
            Path file = packageDir.resolve(elem.handle().name() + ".java");
            Files.writeString(
                    file,
                    elem.source(),
                    StandardCharsets.UTF_8
            );
        }
    }

}