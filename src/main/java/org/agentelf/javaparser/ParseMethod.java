package org.agentelf.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import org.agentelf.model.source.MethodSource;
import org.agentelf.model.source.TypeDescriptionSource;

public class ParseMethod {


    /**
     * Parses a single method declaration from a string.
     *
     * @param methodSource The source code of the method.
     * @return The parsed MethodIntermediate object.
     */
    public MethodSource parse(String methodSource) {
        String normalizedMethodSource = new NormalizeDescription().normalize(methodSource);
        MethodDeclaration methodDeclaration = StaticJavaParser.parseMethodDeclaration(normalizedMethodSource);
        MethodSource methodIntermediate = new MethodSource();
        methodIntermediate.setName(methodDeclaration.getNameAsString());
        methodIntermediate.setReturnType(TypeDescriptionSource.create(methodDeclaration.getType()));
        for (Parameter parameter : methodDeclaration.getParameters()) {
            methodIntermediate.addParameter(TypeDescriptionSource.create(parameter.getType()),
                    parameter.getNameAsString());
        }
        return methodIntermediate;
    }

}