package org.agentelf.javaparser;

import com.github.javaparser.ParseProblemException;
import org.agentelf.model.source.MethodSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParseMethodTest {

    private ParseMethod parseMethod;

    @BeforeEach
    void setUp() {
        parseMethod = new ParseMethod();
    }

    @Test
    void testParseSimpleMethodWithOneParameter() {
        String code = "public void exampleMethod(String input) { System.out.println(input); }";
        MethodSource result = parseMethod.parse(code);

        assertNotNull(result);
        assertEquals("exampleMethod", result.getName());
        // Verification depends on implementation of JavaParserToIntermediateTyp
        assertNotNull(result.getReturnType());
    }

    @Test
    void testParseMethodWithMultipleParameters() {
        String code = "public int calculateSum(int a, double b, String label) { return 0; }";
        MethodSource result = parseMethod.parse(code);

        assertNotNull(result);
        assertEquals("calculateSum", result.getName());
    }

    @Test
    void testParseMethodWithCompileError() {
        String code = "public int calculateSum(int a, double b, String label) {  }";
        MethodSource result = parseMethod.parse(code);

        assertNotNull(result);
        assertEquals("calculateSum", result.getName());
    }

    @Test
    void testParseMethodWithoutBlock() {
        String code = "public int calculateSum(int a, double b, String label);";
        MethodSource result = parseMethod.parse(code);;

        assertNotNull(result);
        assertEquals("calculateSum", result.getName());
    }

    @Test
    void testParseMethodWithNoParameters() {
        String code = "protected String getName() { return \"default\"; }";
        MethodSource result = parseMethod.parse(code);

        assertNotNull(result);
        assertEquals("getName", result.getName());
    }

    @Test
    void testParseInvalidMethodSourceThrowsException() {
        String invalidCode = "public void invalidMethod(String input { Oops }";
        
        assertThrows(ParseProblemException.class, () -> {
            parseMethod.parse(invalidCode);
        });
    }

    @Test
    void testParseEmptyStringThrowsException() {
        assertThrows(ParseProblemException.class, () -> {
            parseMethod.parse("");
        });
    }
}