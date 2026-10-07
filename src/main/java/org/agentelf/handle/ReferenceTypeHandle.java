package org.agentelf.handle;

public record ReferenceTypeHandle(String packageName, String name) implements TypeHandle{

    public String fullQualifiedName() {
        if(packageName.trim().isEmpty()) {
            return name;
        }
        return packageName + "." + name;
    }

}
