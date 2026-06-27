package org.agentelf.model.generate;

import lombok.Data;
import org.agentelf.model.common.WithDeclaration;

import java.util.function.Function;

@Data
public class ExternalType implements WithDeclaration, ExternalOrGenerateType {

    private String declaration;

    @Override
    public Object onExternalClass(Function<ExternalType, Object> onExternal) {
        return onExternal.apply(this);
    }

    @Override
    public Object onGenerateClass(Function<GenerateType, Object> onGenerated) {
        return null;
    }
}
