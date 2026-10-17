package org.agentelf.handle;

import com.github.javaparser.ast.expr.SimpleName;

public record ReferenceTypeHandle(String packageName, String name) implements TypeHandle{

    public String fullQualifiedName() {
        if(packageName.trim().isEmpty()) {
            return name;
        }
        return packageName + "." + name;
    }

    public SimpleName asSimpleName() {
        return new SimpleName(name);
    }

}
