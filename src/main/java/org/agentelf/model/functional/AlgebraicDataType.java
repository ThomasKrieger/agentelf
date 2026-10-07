package org.agentelf.model.functional;

import org.agentelf.sourcebuilder.SourceBuilder;

import java.util.Set;

public interface AlgebraicDataType {

    void addToBuilder(String packageName,
                      SourceBuilder sourceBuilder,
                      FunctionalTypeToJavapoetType functionalTypeToJavapoetType,
                      Set<NotYetBuiltToInterface> notYetBuilt);

}
