package org.agentelf.javaparser;

import com.github.javaparser.ParseProblemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParseFieldTest {

    private ParseField parseField;

    @BeforeEach
    void setUp() {
        parseField = new ParseField();
    }

    @ParameterizedTest
    @CsvSource({
        "'int i;', int, i",
        "'String name;', String, name",
        "'Parser test;', Parser, test"
    })
    void testStandardDescriptions(String description, String expectedType, String expectedName) {
        TypeAndName result = parseField.parseFieldInternal(description);
        assertEquals(expectedType, result.type().asString());
        assertEquals(expectedName, result.name());
    }

    @Test
    void testFieldWithModifiers() {
        TypeAndName result = parseField.parseFieldInternal("private static final long serialVersionUID = 1L;");
        assertEquals("long", result.type().asString());
        assertEquals("serialVersionUID", result.name());
    }

    @Test
    void testFieldWithGenericType() {
        TypeAndName result = parseField.parseFieldInternal("List<String> items;");
        assertEquals("List<String>", result.type().asString());
        assertEquals("items", result.name());
    }

    @Test
    void testMultipleVariablesInOneDeclaration() {
        // The implementation takes the first variable found
        TypeAndName result = parseField.parseFieldInternal("int x, y, z;");
        assertEquals("int", result.type().asString());
        assertEquals("x", result.name());
    }

    @Test
    void testInvalidBodyDeclarationType() {
        // Providing a method instead of a field should trigger the IllegalArgumentException
        String methodDescription = "public void run() {}";
        assertThrows(IllegalArgumentException.class, () -> parseField.parseFieldInternal(methodDescription));
    }

    @Test
    void testMalformedJavaCode() {
        // Providing non-Java code should trigger a Parser exception
        assertThrows(ParseProblemException.class, () -> parseField.parseFieldInternal("not a field at all"));
    }

    @Test
    void testNullInput() {
        assertThrows(RuntimeException.class, () -> parseField.parseFieldInternal(null));
    }

    @Test
    void testEmptyInput() {
        assertThrows(ParseProblemException.class, () -> parseField.parseFieldInternal(""));
    }
}