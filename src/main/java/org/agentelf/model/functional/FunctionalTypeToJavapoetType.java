package org.agentelf.model.functional;

import com.palantir.javapoet.TypeName;
import lombok.AllArgsConstructor;
import org.agentelf.model.function.FunctionDeclarationParser;
import org.agentelf.type.TypeRepo;

@AllArgsConstructor
public class FunctionalTypeToJavapoetType {

    private final TypeRepo typeRepo;

    /**
     * search the type in the following places:
     * 1) in the usedFromPackageName
     * 2) in imports
     * 3) in java.lang either primitive
     * 4) in other jars which are part of the project
     *
     */
    public TypeName toJavapoetType(String usedFromPackageName, String type) {
        FunctionDeclarationParser parser = new ParserFactory().create(type);
        return new CreateFunction(typeRepo,usedFromPackageName).visitType(parser.type()).toJavapoet();
    }

}
