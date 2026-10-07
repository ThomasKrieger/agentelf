package org.agentelf.type;

import com.palantir.javapoet.ArrayTypeName;
import com.palantir.javapoet.TypeName;
import lombok.EqualsAndHashCode;
import org.agentelf.handle.TypeHandle;

import java.util.List;

@EqualsAndHashCode
public class NativeType implements Type, TypeHandle {

    private final NativeTypeEnum nativeTypeEnum;
    private boolean isArray;

    public static NativeType of(String name) {
        return new NativeType(NativeTypeEnum.valueOf(name.toUpperCase()));
    }

    public NativeType(NativeTypeEnum nativeTypeEnum) {
        this.nativeTypeEnum = nativeTypeEnum;
    }

    @Override
    public TypeHandle toHandle() {
        return this;
    }

    @Override
    public TypeName toJavapoet() {
        if(isArray) {
            return ArrayTypeName.of(nativeTypeEnum.getTypeName());
        } else {
            return nativeTypeEnum.getTypeName();
        }
    }

    @Override
    public String simpleName() {
        return nativeTypeEnum.getTypeName().toString();
    }

    @Override
    public void addGenerics(List<Type> generics) {
        throw new RuntimeException("should not be called");
    }

    @Override
    public void setIsArray() {
        this.isArray = true;
    }

}
