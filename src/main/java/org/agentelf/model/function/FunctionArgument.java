package org.agentelf.model.function;

import com.palantir.javapoet.MethodSpec;
import lombok.Builder;
import org.agentelf.type.Type;

@Builder
public record FunctionArgument(Type type, String name) {

    public void addToBuilder(MethodSpec.Builder builder) {
        builder.addParameter(type.toJavapoet(),name);
    }

}
