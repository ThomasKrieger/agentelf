package org.agentelf.type;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.TypeName;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.handle.TypeHandle;

public record ReferenceType(String packageName, String name) implements Type {
    @Override
    public TypeHandle toHandle() {
        return new ReferenceTypeHandle(packageName,name);
    }

    @Override
    public TypeName toJavapoet() {
        return ClassName.get(packageName,name);
    }

    @Override
    public String simpleName() {
        return name;
    }

}
