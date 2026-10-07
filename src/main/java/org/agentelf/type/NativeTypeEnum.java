package org.agentelf.type;

import com.palantir.javapoet.TypeName;
import lombok.Getter;

public enum NativeTypeEnum {
    VOID(TypeName.VOID),
    BOOLEAN(TypeName.BOOLEAN),
    BYTE(TypeName.BYTE),
    SHORT(TypeName.SHORT),
    INT(TypeName.INT),
    LONG(TypeName.LONG),
    CHAR(TypeName.CHAR),
    FLOAT(TypeName.FLOAT),
    DOUBLE(TypeName.DOUBLE);

    @Getter
    private final TypeName typeName;


    NativeTypeEnum(TypeName typeName) {
        this.typeName = typeName;
    }

}
