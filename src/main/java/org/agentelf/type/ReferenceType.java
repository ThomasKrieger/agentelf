package org.agentelf.type;

import com.palantir.javapoet.ArrayTypeName;
import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.ParameterizedTypeName;
import com.palantir.javapoet.TypeName;
import lombok.Getter;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.handle.TypeHandle;

import java.util.List;

@Getter
public class ReferenceType implements Type {

    @Getter
    private final String packageName;
    @Getter
    private final String name;
    private boolean isArray;
    private List<Type> generics;

    public ReferenceType(String packageName,
                         String name) {
        this.packageName = packageName;
        this.name = name;
    }

    @Override
    public TypeHandle toHandle() {
        return new ReferenceTypeHandle(packageName,name);
    }

    @Override
    public TypeName toJavapoet() {
        TypeName typeName =  ClassName.get(packageName,name);
        if(generics != null) {
            typeName = ParameterizedTypeName.get(
                    ClassName.get(packageName,name) ,
                    generics.stream().map(Type::toJavapoet).toList().toArray(new TypeName[0]));
        }
        if(isArray) {
            typeName =  ArrayTypeName.of(typeName);
        }
        return typeName;
    }

    @Override
    public String simpleName() {
        return name;
    }

    @Override
    public void addGenerics(List<Type> generics) {
        this.generics = generics;

    }

    @Override
    public void setIsArray() {
        this.isArray = true;
    }

}
