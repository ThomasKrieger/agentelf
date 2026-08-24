package org.agentelf.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import org.agentelf.model.intermediate.MethodIntermediate;
import org.agentelf.model.intermediate.TypeDescriptionIntermediate;

public class ParseMethod {


    /**
     * Parses a single method declaration from a string.
     *
     * @param methodSource The source code of the method.
     * @return The parsed MethodIntermediate object.
     */
    public MethodIntermediate parse(String methodSource) {
        String normalizedMethodSource = new NormalizeDescription().normalize(methodSource);
        MethodDeclaration methodDeclaration = StaticJavaParser.parseMethodDeclaration(normalizedMethodSource);
        MethodIntermediate methodIntermediate = new MethodIntermediate();
        methodIntermediate.setName(methodDeclaration.getNameAsString());
        methodIntermediate.setReturnType(TypeDescriptionIntermediate.create(methodDeclaration.getType()));
        for (Parameter parameter : methodDeclaration.getParameters()) {
            methodIntermediate.addParameter(TypeDescriptionIntermediate.create(parameter.getType()),
                    parameter.getNameAsString());
        }
        return methodIntermediate;
    }

}