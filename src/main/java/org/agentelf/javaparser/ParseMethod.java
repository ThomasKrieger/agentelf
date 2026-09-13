package org.agentelf.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import org.agentelf.model.tosource.MethodToSource;
import org.agentelf.model.tosource.TypeDescriptionToSource;

public class ParseMethod {


    /**
     * Parses a single method declaration from a string.
     *
     * @param methodSource The source code of the method.
     * @return The parsed MethodIntermediate object.
     */
    public MethodToSource parse(String methodSource) {
        String normalizedMethodSource = new NormalizeDescription().normalize(methodSource);
        MethodDeclaration methodDeclaration = StaticJavaParser.parseMethodDeclaration(normalizedMethodSource);
        MethodToSource methodIntermediate = new MethodToSource();
        methodIntermediate.setName(methodDeclaration.getNameAsString());
        methodIntermediate.setReturnType(TypeDescriptionToSource.create(methodDeclaration.getType()));
        for (Parameter parameter : methodDeclaration.getParameters()) {
            methodIntermediate.addParameter(TypeDescriptionToSource.create(parameter.getType()),
                    parameter.getNameAsString());
        }
        return methodIntermediate;
    }

}