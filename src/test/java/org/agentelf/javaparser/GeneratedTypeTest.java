package org.agentelf.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import org.agentelf.handle.MethodHandle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class GeneratedTypeTest {

    private GeneratedType generatedType;
    private CompilationUnit cu;
    private static final String CLASS_NAME = "TestClass";
    private static final String METHOD_NAME = "testMethod";

    @BeforeEach
    void setUp() throws Exception {
        generatedType = new GeneratedType();
        cu = StaticJavaParser.parse(String.format(
                "public class %s {\n" +
                "    public void %s() {}\n" +
                "    public void other() {}\n" +
                "}", CLASS_NAME, METHOD_NAME));

        // Injecting the private field compilationUnit since no constructor/setter is provided in the source
        Field field = GeneratedType.class.getDeclaredField("compilationUnit");
        field.setAccessible(true);
        field.set(generatedType, cu);
    }

    @Test
    void testSetMethodAnnotation_Success() {
        MethodHandle handle = new MethodHandle(METHOD_NAME);
        String annotation = "@Deprecated";
        
        generatedType.setMethodAnnotation(handle, annotation);

        MethodDeclaration method = cu.getClassByName(CLASS_NAME).get().getMethodsByName(METHOD_NAME).get(0);
        assertTrue(method.getAnnotationByName("Deprecated").isPresent(), "Annotation should be present");
        
        MethodDeclaration other = cu.getClassByName(CLASS_NAME).get().getMethodsByName("other").get(0);
        assertFalse(other.getAnnotationByName("Deprecated").isPresent(), "Annotation should not be on other methods");
    }

    @Test
    void testSetMethodAnnotation_NoMatch() {
        MethodHandle handle = new MethodHandle("nonExistentMethod");
        generatedType.setMethodAnnotation(handle, "@NotNull");

        assertFalse(cu.toString().contains("@NotNull"), "No annotation should have been added");
    }

    @Test
    void testSetMethodDocumentation_Success() {
        MethodHandle handle = new MethodHandle(METHOD_NAME);
        String doc = " This is a test Javadoc ";

        generatedType.setMethodDocumentation(handle, doc);

        MethodDeclaration method = cu.getClassByName(CLASS_NAME).get().getMethodsByName(METHOD_NAME).get(0);
        assertTrue(method.getJavadocComment().isPresent());
        assertEquals(doc, method.getJavadocComment().get().getContent());
    }

    @Test
    void testSetClassAnnotation_Success() {
        String annotation = "@SuppressWarnings(\"all\")";
        generatedType.setClassAnnotation(annotation);

        TypeDeclaration<?> type = cu.getTypes().getFirst().get();
        assertTrue(type.getAnnotationByName("SuppressWarnings").isPresent());
    }

    @Test
    void testSetClassDocumentation_Success() {
        String doc = "Class documentation";
        generatedType.setClassDocumentation(doc);

        TypeDeclaration<?> type = cu.getTypes().getFirst().get();
        assertTrue(type.getJavadocComment().isPresent());
        assertEquals(doc, type.getJavadocComment().get().getContent());
    }

    @Test
    void testSetClassMethods_NoPrimaryType() throws Exception {
        // Edge case: Compilation unit with no primary type (e.g., package-info.java or empty)
        CompilationUnit emptyCu = StaticJavaParser.parse("package org.test;");
        Field field = GeneratedType.class.getDeclaredField("compilationUnit");
        field.setAccessible(true);
        field.set(generatedType, emptyCu);

        // These should not throw exceptions even if primary type is missing
        assertDoesNotThrow(() -> generatedType.setClassAnnotation("@Test"));
        assertDoesNotThrow(() -> generatedType.setClassDocumentation("Docs"));
    }

    @Test
    void testSetMethodAnnotation_MalformedAnnotation() {
        MethodHandle handle = new MethodHandle(METHOD_NAME);
        // This is expected to throw a ParseException from StaticJavaParser
        assertThrows(Exception.class, () -> {
            generatedType.setMethodAnnotation(handle, "invalid_annotation_syntax");
        });
    }

    @Test
    void testMultipleMethodsSameName() {
        // JavaParser handles method overloads; current logic matches by name only
        cu.getClassByName(CLASS_NAME).get().addMethod(METHOD_NAME); 
        MethodHandle handle = new MethodHandle(METHOD_NAME);
        
        generatedType.setMethodAnnotation(handle, "@Override");

        long count = cu.getClassByName(CLASS_NAME).get().getMethodsByName(METHOD_NAME).stream()
                .filter(m -> m.getAnnotationByName("Override").isPresent())
                .count();
        
        assertEquals(2, count, "Both overloaded methods should be annotated based on current logic");
    }
}