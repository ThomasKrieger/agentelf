package org.agentelf.model.tosource;

import com.palantir.javapoet.TypeSpec;

public interface TypeSource {

    String asString();
    TypeSpec.Builder asBuilder();

}
