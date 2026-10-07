package org.agentelf.sourcebuilder;

import com.palantir.javapoet.CodeBlock;
import com.palantir.javapoet.TypeName;

import java.util.List;

public class CodeBlockBuilder {

    private final CodeBlock.Builder builder;

    public CodeBlockBuilder(CodeBlock.Builder builder) {
        this.builder = builder;
    }

    public static CodeBlock creatLiteral(Object object) {
        return CodeBlock.of("$L", object);
    }

    public static CodeBlock createStringLiteral(String string) {
      return CodeBlock.of("$S", string);
    }

    public static CodeBlock createMethodCall(CodeBlock receiving, String name, List<CodeBlock> arguments) {
        CodeBlock joinedArguments = CodeBlock.join(arguments, ",");
        CodeBlock methodCall = CodeBlock.of(name + "(" + joinedArguments+ ")");
        return CodeBlock.join(List.of(receiving,methodCall),".");
    }

    public static CodeBlock createLambdaExpression(List<CodeBlock> variables,List<CodeBlock> statements) {
        CodeBlock joinedArguments = CodeBlock.join(variables, ",");
        CodeBlock.Builder builder = CodeBlock.builder().beginControlFlow( "(" + joinedArguments + ") -> " );
        for(CodeBlock statement : statements) {
            builder.addStatement(statement);
        }
        builder.endControlFlow();
        return builder.build();
    }

    public static CodeBlock createCallNew(TypeName typeName) {
        return CodeBlock.of("new $T()",typeName);
    }

    public void addComment(String text) {
        builder.add("// " + text);
    }

    public void addCodeBlock(CodeBlock codeBlock) {
        builder.add(codeBlock);
    }

}
