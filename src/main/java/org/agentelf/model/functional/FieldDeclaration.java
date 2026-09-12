package org.agentelf.model.functional;

import com.palantir.javapoet.TypeSpec;

public interface FieldDeclaration {

    void addToBuilder(String packageName, TypeSpec.Builder typeBuilder);

}
