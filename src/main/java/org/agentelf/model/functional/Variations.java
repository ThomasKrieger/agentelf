package org.agentelf.model.functional;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.TypeSpec;
import org.agentelf.sourcebuilder.SourceBuilder;

import java.util.List;

public record Variations(String name, List<String> either) implements AlgebraicDataType {
    @Override
    public void addToBuilder(String packageName, SourceBuilder sourceBuilder, FunctionalTypeToJavapoetType functionalTypeToJavapoetType) {
        ClassName interfaceName = ClassName.get(packageName, name);
        sourceBuilder.newInterface(packageName,name);
        for(String implementing : either) {
            TypeSpec.Builder impl = sourceBuilder.get(packageName, implementing);
            if(impl == null) {
                impl = sourceBuilder.newInterface(packageName,implementing);
            }
            impl.addSuperinterface(interfaceName);
        }
    }
}
