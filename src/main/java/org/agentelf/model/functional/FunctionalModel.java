package org.agentelf.model.functional;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.sourcebuilder.SourceBuilder;
import org.agentelf.type.TypeRepo;

import java.util.List;

public record FunctionalModel(@JsonProperty("package") String packageName,
                              List<Variations> variations,
                              List<Immutable> immutables,
                              List<FunctionWithPromptFunctional> functions) {

    public void addToBuilder(SourceBuilder sourceBuilder, FunctionalTypeToJavapoetType functionalTypeToJavapoetType) {
        if(immutables != null) {
            for(Immutable type : immutables) {
                type.addToBuilder(packageName,sourceBuilder, functionalTypeToJavapoetType);
            }
        }
        if(variations != null) {
            for(Variations type : variations) {
                type.addToBuilder(packageName,sourceBuilder, functionalTypeToJavapoetType);
            }
        }
    }

    public void addFunctionsToSourceModel(TypeRepo typeRepo, ToSourceModel model) {
        if(functions != null) {
            for(FunctionWithPromptFunctional function : functions) {
                function.toFunction(packageName,typeRepo).addToSourceModelType(model);
            }
        }
    }

}
