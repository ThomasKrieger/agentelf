package org.agentelf.sourcebuilder;

import com.palantir.javapoet.TypeSpec;
import org.agentelf.handle.ReferenceTypeHandle;

import java.util.HashMap;
import java.util.Map;


public class SourceBuilder {

    private final Map<ReferenceTypeHandle,TypeSpec.Builder> map = new HashMap<>();

    public TypeSpec.Builder newInterface(String packageName, String name) {
        TypeSpec.Builder builder = TypeSpec.interfaceBuilder(name);
        ReferenceTypeHandle referenceTypeHandle = new ReferenceTypeHandle(packageName,name);
        map.put(referenceTypeHandle,builder);
        return builder;
    }

    public TypeSpec.Builder newRecord(String packageName, String name) {
        TypeSpec.Builder builder = TypeSpec.recordBuilder(name);
        ReferenceTypeHandle referenceTypeHandle = new ReferenceTypeHandle(packageName,name);
        map.put(referenceTypeHandle,builder);
        return builder;
    }

    public TypeSpec.Builder get(String packageName,String name) {
        return map.get(new ReferenceTypeHandle(packageName,name));
    }

}
