package org.agentelf.model.functional;

import com.palantir.javapoet.MethodSpec;
import com.palantir.javapoet.TypeSpec;
import org.agentelf.sourcebuilder.SourceBuilder;

import java.util.List;
import java.util.Set;

public record Immutable(String name, List<FieldDeclaration> fields) implements AlgebraicDataType {

    @Override
    public void addToBuilder(String packageName,
                             SourceBuilder sourceBuilder,
                             FunctionalTypeToJavapoetType functionalTypeToJavapoetType,
                             Set<NotYetBuiltToInterface> notYetBuilt) {
        TypeSpec.Builder typeBuilder = sourceBuilder.newRecord(packageName, name);
        MethodSpec.Builder recordConstructor = MethodSpec.constructorBuilder();
        for(FieldDeclaration field : fields) {
            field.addToBuilder(packageName,recordConstructor, functionalTypeToJavapoetType);
        }
        typeBuilder.recordConstructor(recordConstructor.build());
    }

}