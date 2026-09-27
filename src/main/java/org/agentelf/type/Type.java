package org.agentelf.type;

import com.palantir.javapoet.TypeName;
import org.agentelf.handle.TypeHandle;

public interface Type {

    TypeHandle toHandle();
    TypeName toJavapoet();

}
