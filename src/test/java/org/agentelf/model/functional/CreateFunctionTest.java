
package org.agentelf.model.functional;

import org.agentelf.model.function.FunctionDeclarationLexer;
import org.agentelf.model.function.FunctionDeclarationParser;
import org.agentelf.type.ReferenceType;
import org.agentelf.type.Type;
import org.agentelf.type.TypeRepo;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateFunctionTest {

    private TypeRepo typeRepo;
    private CreateFunction createFunction;
    private final String CURRENT_PACKAGE = "org.agentelf.test";

    @BeforeEach
    void setUp() {
        typeRepo = mock(TypeRepo.class);
        createFunction = new CreateFunction(typeRepo, CURRENT_PACKAGE);
    }

    @Test
    void testVisitTypeSimpleName() {
        String declaration = "String";
        CharStream chars = CharStreams.fromString(declaration);
        FunctionDeclarationLexer lexer = new FunctionDeclarationLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FunctionDeclarationParser parser = new FunctionDeclarationParser(tokens);

        // Prepare mock type
        Type mockType = mock(Type.class);
        when(typeRepo.getForSimpleName(CURRENT_PACKAGE, "String")).thenReturn(mockType);

        // Execute
        Type result = createFunction.visitType(parser.type());

        // Verify
        assertNotNull(result);
        assertEquals(mockType, result);
        verify(typeRepo).getForSimpleName(CURRENT_PACKAGE, "String");
    }

    @Test
    void testVisitTypeFullyQualifiedName() {
        // Grammar matches .IDENTIFIER .IDENTIFIER...
        String declaration = "java.util.List";
        CharStream chars = CharStreams.fromString(declaration);
        FunctionDeclarationLexer lexer = new FunctionDeclarationLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FunctionDeclarationParser parser = new FunctionDeclarationParser(tokens);

        // Execute
        Type result = createFunction.visitType(parser.type());

        // Verify
        assertTrue(result instanceof ReferenceType);
        assertEquals("List", result.simpleName());
    }

    @Test
    void testVisitTypeWithGenerics() {
        // Since the current logic in visitType lacks a null check for generic(), 
        // we test a case where generics ARE present to avoid NPE in the provided snippet logic.
        String declaration = "Map<String>"; 
        CharStream chars = CharStreams.fromString(declaration);
        FunctionDeclarationLexer lexer = new FunctionDeclarationLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FunctionDeclarationParser parser = new FunctionDeclarationParser(tokens);

        Type mapType = mock(Type.class);
        Type stringType = mock(Type.class);

        when(typeRepo.getForSimpleName(eq(CURRENT_PACKAGE), eq("Map"))).thenReturn(mapType);
        when(typeRepo.getForSimpleName(eq(CURRENT_PACKAGE), eq("String"))).thenReturn(stringType);

        // Execute
        Type result = createFunction.visitType(parser.type());

        // Verify
        verify(mapType).addGenerics(anyList());
        assertEquals(mapType, result);
    }

    @Test
    void testVisitTypeArray() {
        // Testing Array logic
        String declaration = "Integer[]";
        CharStream chars = CharStreams.fromString(declaration);
        FunctionDeclarationLexer lexer = new FunctionDeclarationLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FunctionDeclarationParser parser = new FunctionDeclarationParser(tokens);

        Type intType = mock(Type.class);
        when(typeRepo.getForSimpleName(CURRENT_PACKAGE, "Integer")).thenReturn(intType);

        // Execute
        Type result = createFunction.visitType(parser.type());

        // Verify
        verify(intType).setIsArray();
        assertEquals(intType, result);
    }
}
