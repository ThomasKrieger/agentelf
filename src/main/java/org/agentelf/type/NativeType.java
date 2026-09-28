package org.agentelf.type;

import com.palantir.javapoet.TypeName;
import org.agentelf.handle.TypeHandle;

public enum NativeType implements Type, TypeHandle {
    VOID(TypeName.VOID),
    BOOLEAN(TypeName.BOOLEAN),
    BYTE(TypeName.BYTE),
    SHORT(TypeName.SHORT),
    INT(TypeName.INT),
    LONG(TypeName.LONG),
    CHAR(TypeName.CHAR),
    FLOAT(TypeName.FLOAT),
    DOUBLE(TypeName.DOUBLE);

    private final TypeName typeName;

    NativeType(TypeName typeName) {
        this.typeName = typeName;
    }

    @Override
    public TypeHandle toHandle() {
        return this;
    }

    @Override
    public TypeName toJavapoet() {
        return typeName;
    }

    @Override
    public String simpleName() {
        return typeName.toString();
    }


}
