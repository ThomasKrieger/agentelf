package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.functional.FunctionalModel;
import org.agentelf.model.functional.FunctionalTypeToJavapoetType;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.model.tosource.TypeToSource;
import org.agentelf.sourcebuilder.ReferenceTypeHandleAndSource;
import org.agentelf.sourcebuilder.SourceBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FunctionalModelToSourceModel {

    @Action(arguments = {"model"}, returnVariable = "toSourceModel")
    public ToSourceModel functionalModelToSourceModel(FunctionalModel model) throws IOException {
        FunctionalTypeToJavapoetType functionalTypeToJavapoetType = new FunctionalTypeToJavapoetType();
        SourceBuilder sourceBuilder = new SourceBuilder();
        model.addToBuilder(sourceBuilder, functionalTypeToJavapoetType);

        Map<ReferenceTypeHandle, TypeToSource> handleToModel = new HashMap<>();
        for(ReferenceTypeHandleAndSource elem : sourceBuilder.build()) {
            TypeToSource typeToSource = new TypeToSource();
            typeToSource.setSource(elem.source());
            handleToModel.put(elem.handle(),typeToSource);
        }
        return new ToSourceModel(handleToModel);
    }

}