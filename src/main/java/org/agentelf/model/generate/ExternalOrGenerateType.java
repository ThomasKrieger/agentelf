package org.agentelf.model.generate;

import java.util.function.Function;

public interface ExternalOrGenerateType {

    Object onExternalClass(Function<ExternalType,Object> onExternal);
    Object onGenerateClass(Function<GenerateType,Object> onGenerated);

}
