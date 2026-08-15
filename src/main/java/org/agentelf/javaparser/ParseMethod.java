package org.agentelf.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import org.agentelf.model.intermediate.MethodIntermediate;

public class ParseMethod {

    private final JavaParserToIntermediateTyp javaParserToIntermediateTyp = new JavaParserToIntermediateTyp();

    /**
     * Parses a single method declaration from a string.
     *
     * @param methodSource The source code of the method.
     * @return The parsed MethodIntermediate object.
     */
    public MethodIntermediate parse(String methodSource) {
        MethodDeclaration methodDeclaration = StaticJavaParser.parseMethodDeclaration(methodSource);
        MethodIntermediate methodIntermediate = new MethodIntermediate();
        methodIntermediate.setName(methodDeclaration.getNameAsString());
        methodIntermediate.setReturnType(javaParserToIntermediateTyp.map(methodDeclaration.getType()));
        for (Parameter parameter : methodDeclaration.getParameters()) {
            methodIntermediate.addParameter(javaParserToIntermediateTyp.map(parameter.getType()),
                    parameter.getNameAsString());
        }
        return methodIntermediate;
    }

    public static void main(String[] args) {
        String code = "private int i;";

        try {
            BodyDeclaration method = StaticJavaParser.parseBodyDeclaration(code);
            System.out.println("Parsed method name: " + method.isFieldDeclaration());
            System.out.println("Parsed method name: " + method.getClass());

        } catch (Exception e) {
            System.err.println("Failed to parse method: " + e.getMessage());
        }
    }
}