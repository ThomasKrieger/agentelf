package org.agentelf.model.functional;

import com.palantir.javapoet.TypeSpec;

public record FieldDeclarationByTypeOnly(String type) implements FieldDeclaration {
    @Override
    public void addToBuilder(String packageName, TypeSpec.Builder typeBuilder) {

    }
}
