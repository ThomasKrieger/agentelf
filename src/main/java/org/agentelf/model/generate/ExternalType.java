package org.agentelf.model.generate;

import lombok.Data;

import java.util.function.Function;

@Data
public class ExternalType implements WithDeclaration, ExternalOrJavaType {

    private String declaration;

    @Override
    public Object onExternalClass(Function<ExternalType, Object> onExternal) {
        return onExternal.apply(this);
    }

    @Override
    public Object onGeneratedClass(Function<JavaType, Object> onGenerated) {
        return null;
    }
}
