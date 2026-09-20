package org.agentelf.model.functional;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.agentelf.sourcebuilder.SourceBuilder;

import java.util.List;

public record AlgebraicDataTypeModel(@JsonProperty("package") String packageName,
                                     List<Variations> variations,
                                     List<Immutable> immutables) {

    public void addToBuilder(SourceBuilder sourceBuilder, FunctionalTypeToJavapoetType functionalTypeToJavapoetType) {
        for(Immutable type : immutables) {
            type.addToBuilder(packageName,sourceBuilder, functionalTypeToJavapoetType);
        }
        for(Variations type : variations) {
            type.addToBuilder(packageName,sourceBuilder, functionalTypeToJavapoetType);
        }
    }

}
