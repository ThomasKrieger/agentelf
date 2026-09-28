
package org.agentelf.model.functional;

import org.agentelf.model.function.Function;
import org.agentelf.type.Type;
import org.agentelf.type.TypeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for FunctionWithPromptFunctional covering various edge cases of the DSL parsing
 * and object construction.
 */
class FunctionWithPromptFunctionalTest {

    @Mock
    private TypeRepo typeRepo;

    @Mock
    private Type mockTypeInt;

    @Mock
    private Type mockTypeString;

    @Mock
    private Type mockTypeVoid;

    private final String defaultPackage = "com.example.test";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Setup mock type resolution behavior
        when(typeRepo.getForSimpleName(anyString(), eq("Int"))).thenReturn(mockTypeInt);
        when(typeRepo.getForSimpleName(anyString(), eq("String"))).thenReturn(mockTypeString);
        when(typeRepo.getForSimpleName(anyString(), eq("Void"))).thenReturn(mockTypeVoid);

        when(mockTypeString.simpleName()).thenReturn("String");
    }

    @Test
    @DisplayName("Test full declaration with multiple arguments and documentation")
    void testToFunction_FullDeclaration() {
        String declaration = "Int calculateSum(Int a, Int b)";
        String prompt = "Add two numbers together";
        String doc = "Returns the integer sum of a and b";

        FunctionWithPromptFunctional functional = new FunctionWithPromptFunctional(declaration, prompt, doc);
        Function result = functional.toFunction(defaultPackage, typeRepo);

        assertNotNull(result);
        assertEquals("calculateSum", result.name());
        assertEquals(prompt, result.prompt());
        assertEquals(doc, result.documentation());
        assertEquals(mockTypeInt, result.returnType());
        assertEquals(2, result.arguments().size());
        
        assertEquals("a", result.arguments().get(0).name());
        assertEquals(mockTypeInt, result.arguments().get(0).type());
        
        assertEquals("b", result.arguments().get(1).name());
        assertEquals(mockTypeInt, result.arguments().get(1).type());
    }

    @Test
    @DisplayName("Test short-form argument (Type only) mapping")
    void testToFunction_ShortFormArguments() {
        // In the grammar, IDENTIFIER without COLON results in name being lowercase of Type
        String declaration = "Void process(String)";
        
        FunctionWithPromptFunctional functional = new FunctionWithPromptFunctional(declaration, "p", "d");
        Function result = functional.toFunction(defaultPackage, typeRepo);

        assertEquals(1, result.arguments().size());
        // Logic: Character.toLowerCase(String.charAt(0)) + "tring" -> "string"
        assertEquals("string", result.arguments().get(0).name());
        assertEquals(mockTypeString, result.arguments().get(0).type());
    }

    @Test
    @DisplayName("Test function with no arguments")
    void testToFunction_NoArguments() {
        String declaration = "Void ping()";
        
        FunctionWithPromptFunctional functional = new FunctionWithPromptFunctional(declaration, null, null);
        Function result = functional.toFunction(defaultPackage, typeRepo);

        assertEquals("ping", result.name());
        assertTrue(result.arguments().isEmpty());
        assertEquals(mockTypeVoid, result.returnType());
        assertNull(result.prompt());
        assertNull(result.documentation());
    }

    @Test
    @DisplayName("Test handling of whitespace and formatting")
    void testToFunction_WhitespaceFormatting() {
        String declaration = " Int  spacedFunc  ( String  input )  ";
        
        FunctionWithPromptFunctional functional = new FunctionWithPromptFunctional(declaration, "", "");
        Function result = functional.toFunction(defaultPackage, typeRepo);

        assertEquals("spacedFunc", result.name());
        assertEquals(1, result.arguments().size());
        assertEquals("input", result.arguments().get(0).name());
        assertEquals(mockTypeString, result.arguments().get(0).type());
        assertEquals(mockTypeInt, result.returnType());
    }

    @Test
    @DisplayName("Test underscore and number identifiers")
    void testToFunction_ComplexIdentifiers() {
        when(typeRepo.getForSimpleName(anyString(), eq("My_Type_2"))).thenReturn(mockTypeString);
        
        String declaration = "Int _internal_99(My_Type_2 val_1) : ";
        
        FunctionWithPromptFunctional functional = new FunctionWithPromptFunctional(declaration, "p", "d");
        Function result = functional.toFunction(defaultPackage, typeRepo);

        assertEquals("_internal_99", result.name());
        assertEquals("val_1", result.arguments().get(0).name());
        assertEquals(mockTypeString, result.arguments().get(0).type());
    }

    @Test
    @DisplayName("Test empty prompt and documentation")
    void testToFunction_EmptyStrings() {
        String declaration = "Void test()";
        FunctionWithPromptFunctional functional = new FunctionWithPromptFunctional(declaration, "", "");
        
        Function result = functional.toFunction(defaultPackage, typeRepo);
        
        assertEquals("", result.prompt());
        assertEquals("", result.documentation());
    }

    @Test
    @DisplayName("Test that the correct package is passed to TypeRepo")
    void testToFunction_PackageResolution() {
        String specificPackage = "org.agentelf.custom";
        String declaration = "Int fn()";
        
        FunctionWithPromptFunctional functional = new FunctionWithPromptFunctional(declaration, "p", "d");
        functional.toFunction(specificPackage, typeRepo);

        // Verify that the parser requested the type from the correct package
        verify(typeRepo).getForSimpleName(eq(specificPackage), eq("Int"));
    }

}
