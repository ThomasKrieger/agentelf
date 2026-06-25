package org.agentelf.model.generate;

import java.util.function.Function;

public interface ExternalOrJavaType {

    Object onExternalClass(Function<ExternalType,Object> onExternal);
    Object onGeneratedClass(Function<JavaType,Object> onGenerated);

}
