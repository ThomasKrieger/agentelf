package org.agentelf.model.functional;

import com.palantir.javapoet.TypeSpec;
import org.agentelf.sourcebuilder.SourceBuilder;

import java.util.List;

public record SumType(String name, List<FieldDeclaration> and) implements AlgebraicDataType {
    @Override
    public void addToBuilder(String packageName, SourceBuilder sourceBuilder) {
        TypeSpec.Builder typeBuilder = sourceBuilder.newRecord(packageName, name);
        for(FieldDeclaration field : and) {
            field.addToBuilder(packageName,typeBuilder);
        }
    }
}
