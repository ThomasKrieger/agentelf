package org.agentelf.model.functional;

import com.palantir.javapoet.TypeSpec;

public record FieldDeclarationByNameAndType(String type, String name) implements FieldDeclaration {
    @Override
    public void addToBuilder(String packageName, TypeSpec.Builder typeBuilder) {

    }
}
