package org.agentelf.model.functional;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.TypeSpec;
import org.agentelf.sourcebuilder.SourceBuilder;

import java.util.List;

public record ProductType(String name, List<String> or) implements AlgebraicDataType {

    @Override
    public void addToBuilder(String packageName, SourceBuilder sourceBuilder) {
        ClassName interfaceName = ClassName.get(packageName, name);
        sourceBuilder.newInterface(packageName,name);
        for(String implementing : or) {
            TypeSpec.Builder impl = sourceBuilder.get(packageName, implementing);
            if(impl == null) {
                impl = sourceBuilder.newInterface(packageName,implementing);
            }
            impl.addSuperinterface(interfaceName);
        }
    }

}
