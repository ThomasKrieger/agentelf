
package org.agentelf.model.type;

import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.ClassSource;
import org.agentelf.model.source.MethodSource;
import org.agentelf.model.unittest.UnitTestLLM;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for TypeModelToIntermediateTypeList.
 * Tests the conversion of fields, methods, and general class structure.
 */
public class TypeToTypeSourceTest {

    @Test
    public void testTransform() {
        // Setup
        TypeToTypeSource converter = new TypeToTypeSource();

        // 1. Define a Method for the Type
        String methodDoc = "Calculates the total sum.";
        String methodPrompt = "Generate a sum method";
        String methodDecl = "public int sum(int a, int b)";
        Method methodModel = new Method(emptyList(),methodDecl, methodDoc, methodPrompt,  "sum");

        // 2. Define a Type (representing a class)
        // Note: The Type record constructor is assumed based on the usage in the Action class.
        // Order: name, fields, methods, interfaces, unitTest
        Field fieldDesc =  new Field(emptyList(),"private String name;");
        Type classType = new Type(
                "UserAccount",
                "Documentation",
                emptyList(),
                List.of(fieldDesc),
                List.of(methodModel),
                List.of("java.io.Serializable"), // Test implements/interfaces
                new UnitTestLLM()
        );

        // 3. Define the TypeModel containing the class
        String packageName = "org.agentelf.test";
        TypeModel model = new TypeModel(
                packageName,
                emptyList(),
                List.of(classType),
                emptyList()
        );

        // Execute
        List<AbstractTypeSource> resultList = converter.transform(model);

        // Assertions
        assertNotNull(resultList, "Resulting list should not be null");
        assertEquals(1, resultList.size(), "Should have converted one class");

        AbstractTypeSource intermediate = resultList.getFirst();
        assertInstanceOf(ClassSource.class, intermediate, "Intermediate should be of type ClassIntermediate");
        ClassSource classIntermediate = (ClassSource) intermediate;

        // Verify Basic Metadata
        assertEquals("UserAccount", classIntermediate.getName(), "Class name should match");
        assertEquals("Documentation", classIntermediate.getDocumentation());
        assertEquals(packageName, classIntermediate.getPackageName(), "Package name should match");
        assertInstanceOf(UnitTestLLM.class, classIntermediate.getUnitTest(), "UnitTest should be initialized");

        // Verify Fields
        assertEquals(1, classIntermediate.getFields().size(), "Should have 1 field");
        // Detailed field parsing is handled by the ParseField dependency inside addField

        // Verify Methods
        assertEquals(1, classIntermediate.getDeclaredMethods().size(), "Should have 1 method");
        MethodSource methodSource = classIntermediate.getDeclaredMethods().getFirst();
        assertEquals(methodDoc, methodSource.getDocumentation(), "Method documentation should match");
        assertEquals(methodPrompt, methodSource.getPrompt(), "Method prompt should match");

        // Verify Implements
        assertEquals(1, classIntermediate.getImplementList().size());
        assertEquals("java.io.Serializable", classIntermediate.getImplementList().getFirst().getLabelForTemplate());
    }
}
