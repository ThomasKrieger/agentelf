package org.agentelf.model.tosource;

import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.TypeSpec;
import lombok.AllArgsConstructor;
import org.agentelf.handle.ReferenceTypeHandle;

@AllArgsConstructor
public class TypeSourceSourceBuilder implements TypeSource {

    private final ReferenceTypeHandle referenceTypeHandle;
    private final TypeSpec.Builder typeSpecBuilder;

    @Override
    public String asString() {
        JavaFile javaFile = JavaFile.builder(referenceTypeHandle.packageName(),typeSpecBuilder.build())
                .build();
        return javaFile.toString();
    }

    @Override
    public TypeSpec.Builder asBuilder() {
        return typeSpecBuilder;
    }
}
