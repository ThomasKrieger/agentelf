package org.agentelf.model.functional;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.TypeSpec;
import org.agentelf.sourcebuilder.SourceBuilder;

import java.util.List;
import java.util.Set;

public record Variations(String name, List<String> either) implements AlgebraicDataType {
    @Override
    public void addToBuilder(String packageName,
                             SourceBuilder sourceBuilder,
                             FunctionalTypeToJavapoetType functionalTypeToJavapoetType,
                             Set<NotYetBuiltToInterface> notYetBuilt) {
        ClassName interfaceName = ClassName.get(packageName, name);
        sourceBuilder.newInterface(packageName,name);
        for(String implementing : either) {
            TypeSpec.Builder impl = sourceBuilder.get(packageName, implementing);
            if(impl == null) {
                notYetBuilt.add(new NotYetBuiltToInterface(implementing,interfaceName));
            } else {
                impl.addSuperinterface(interfaceName);
            }
        }
    }
}
