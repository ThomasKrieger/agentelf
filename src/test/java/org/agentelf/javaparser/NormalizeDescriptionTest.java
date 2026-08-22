package org.agentelf.javaparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class NormalizeDescriptionTest {

    private final NormalizeDescription normalizer = new NormalizeDescription();

    @Test
    public void testNormalizeProvidedData() {
        // int i -> int i;
        assertEquals("int i;", normalizer.normalize("int i"));
        
        // int i; -> int i;
        assertEquals("int i;", normalizer.normalize("int i;"));
        
        // public void exec(String x){} -> public void exec(String x){}
        assertEquals("public void exec(String x){}", normalizer.normalize("public void exec(String x){}"));
        
        // public void update(String x) -> public void update(String x);
        assertEquals("public void update(String x);", normalizer.normalize("public void update(String x)"));
        
        // void insert(String x); -> void insert(String x);
        assertEquals("void insert(String x);", normalizer.normalize("void insert(String x);"));
    }

    @Test
    public void testNormalizeEdgeCases() {
        // Null input
        assertNull(normalizer.normalize(null));
        
        // Empty string
        assertEquals("", normalizer.normalize(""));
        
        // Blank string (whitespace only)
        assertEquals("   ;", normalizer.normalize("   "));
    }

    @Test
    public void testNormalizeWithTrailingWhitespace() {
        // Ends with semicolon followed by space
        assertEquals("int x; ", normalizer.normalize("int x; "));
        
        // Ends with brace followed by newline/space
        assertEquals("void test(){} \n", normalizer.normalize("void test(){} \n"));
        
        // Needs semicolon, has trailing space
        assertEquals("int x ;", normalizer.normalize("int x "));
    }

    @Test
    public void testNormalizeAlreadyCorrect() {
        assertEquals("return true;", normalizer.normalize("return true;"));
        assertEquals("if(a){}", normalizer.normalize("if(a){}"));
    }
}