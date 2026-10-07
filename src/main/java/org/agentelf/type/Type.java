package org.agentelf.type;

import com.palantir.javapoet.TypeName;
import org.agentelf.handle.TypeHandle;

import java.util.List;

public interface Type {

    TypeHandle toHandle();
    TypeName toJavapoet();
    String simpleName();

    void addGenerics(List<Type> generics);
    void setIsArray();
}
