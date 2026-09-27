package org.agentelf.model.tosource;

import com.palantir.javapoet.TypeSpec;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TypeSourceString implements TypeSource {

    private final String source;

    @Override
    public String asString() {
        return source;
    }

    @Override
    public TypeSpec.Builder asBuilder() {
        return null;
    }
}
