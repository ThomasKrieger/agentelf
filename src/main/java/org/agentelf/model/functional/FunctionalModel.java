package org.agentelf.model.functional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.palantir.javapoet.TypeSpec;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.sourcebuilder.SourceBuilder;
import org.agentelf.type.ReferenceTypeRepo;
import org.agentelf.type.TypeRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record FunctionalModel(@JsonProperty("package") String packageName,
                              List<Variations> variations,
                              List<Immutable> immutables,
                              List<FunctionWithPromptFunctional> functions) {

    public void addToBuilder(SourceBuilder sourceBuilder,
                             FunctionalTypeToJavapoetType functionalTypeToJavapoetType) {
        Set<NotYetBuiltToInterface> notYetBuilt = new HashSet<>();
        if(immutables != null) {
            for(Immutable type : immutables) {
                type.addToBuilder(packageName,sourceBuilder, functionalTypeToJavapoetType,notYetBuilt);
            }
        }
        if(variations != null) {
            for(Variations type : variations) {
                type.addToBuilder(packageName,sourceBuilder, functionalTypeToJavapoetType,notYetBuilt);
            }
        }
        for(NotYetBuiltToInterface notYet : notYetBuilt) {
            TypeSpec.Builder impl = sourceBuilder.get(packageName, notYet.notYetBuilt());
            if(impl == null){
                impl = sourceBuilder.newClass(packageName, notYet.notYetBuilt());
                impl.addSuperinterface(notYet.parent());
            }
        }
    }

    public void addFunctionsToSourceModel(TypeRepo typeRepo, ToSourceModel model) {
        if(functions != null) {
            for(FunctionWithPromptFunctional function : functions) {
                function.toFunction(packageName,typeRepo).addToSourceModelType(model);
            }
        }
    }

    public void addTypesToRepo(ReferenceTypeRepo referenceTypeRepo) {
        if(immutables != null) {
            for(Immutable type : immutables) {
                 referenceTypeRepo.put(new ReferenceTypeHandle(packageName,type.name()));
            }
        }
        if(variations != null) {
            for(Variations type : variations) {
                referenceTypeRepo.put(new ReferenceTypeHandle(packageName,type.name()));
                for(String implementing : type.either()) {
                    referenceTypeRepo.put(new ReferenceTypeHandle(packageName,implementing));
                }
            }
        }
        addInheritanceToRepo(referenceTypeRepo);
    }

    private void addInheritanceToRepo(ReferenceTypeRepo referenceTypeRepo) {
        if(variations != null) {
            for(Variations type : variations) {
                for(String implementing : type.either()) {
                    referenceTypeRepo.addInheritanceRelation(new ReferenceTypeHandle(packageName,type.name()),
                            new ReferenceTypeHandle(packageName,implementing));
                }
            }
        }
    }

    private void addUsageToRepo(ReferenceTypeRepo referenceTypeRepo) {
        if(immutables != null) {
            for(Immutable type : immutables) {
                referenceTypeRepo.put(new ReferenceTypeHandle(packageName,type.name()));
            }
        }
        if(variations != null) {
            for(Variations type : variations) {
                referenceTypeRepo.put(new ReferenceTypeHandle(packageName,type.name()));
            }
        }
    }
}
