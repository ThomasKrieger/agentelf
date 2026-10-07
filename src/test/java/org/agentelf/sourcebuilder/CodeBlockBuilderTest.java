package org.agentelf.sourcebuilder;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.CodeBlock;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CodeBlockBuilderTest {

    @Test
    public void addComment() {
        CodeBlock.Builder builder = CodeBlock.builder();
        CodeBlockBuilder codeBlockBuilder = new CodeBlockBuilder(builder);
        codeBlockBuilder.addComment("this is a comment");
        assertEquals("// this is a comment", builder.build().toString());
    }

    @Test
    public void createCallNew() {
        CodeBlock codeBlock = CodeBlockBuilder.createCallNew(ClassName.get("test", "Test"));
        assertEquals("new test.Test()", codeBlock.toString());
    }

    @Test
    public void createStringLiteral() {
        CodeBlock codeBlock = CodeBlockBuilder.createStringLiteral("this is a String");
        assertEquals("\"this is a String\"", codeBlock.toString());
    }

    @Test
    public void createMethodCall() {
        CodeBlock codeBlock = CodeBlockBuilder.createMethodCall(CodeBlock.of("a") , "createMethodCall" , List.of(CodeBlockBuilder.creatLiteral(true)));
        assertEquals("a.createMethodCall(true)", codeBlock.toString());
    }

    @Test
    public void createLambdaExpression() {
        CodeBlock codeBlock = CodeBlockBuilder.createLambdaExpression(Collections.emptyList(),
                List.of(CodeBlockBuilder.createCallNew(ClassName.get("test", "Test"))));
        assertEquals("""
() ->  {
  new test.Test();
}
""", codeBlock.toString());
    }

}
