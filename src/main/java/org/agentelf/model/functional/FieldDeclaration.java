package org.agentelf.model.functional;

import com.palantir.javapoet.MethodSpec;
import com.palantir.javapoet.TypeName;

public record FieldDeclaration(String type, String name) {

    public void addToBuilder(String packageName, MethodSpec.Builder builder, FunctionalTypeToJavapoetType functionalTypeToJavapoetType) {
        TypeName typeName = functionalTypeToJavapoetType.toJavapoetType(packageName, type);
        if(name != null) {
            builder.addParameter(typeName,name);
        } else {
            builder.addParameter(typeName,type);
        }
    }
}
