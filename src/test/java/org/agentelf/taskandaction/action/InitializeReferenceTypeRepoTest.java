package org.agentelf.taskandaction.action;

import org.agentelf.type.ReferenceTypeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InitializeReferenceTypeRepo.
 * Tests various scenarios including multiple files, multiple types per file, 
 * default packages, and edge cases like missing or invalid directories.
 */
class InitializeReferenceTypeRepoTest {

    @TempDir
    Path tempDir;

    @Test
    void testInitializeReferenceTypeRepo_Success() throws IOException {
        // Prepare main source directory
        Path mainSrc = tempDir.resolve("src/main/java");
        Files.createDirectories(mainSrc.resolve("org/agentelf/example"));
        Files.writeString(mainSrc.resolve("org/agentelf/example/Service.java"),
                "package org.agentelf.example; public class Service {}");

        // Prepare test source directory
        Path testSrc = tempDir.resolve("src/test/java");
        Files.createDirectories(testSrc.resolve("org/agentelf/example"));
        Files.writeString(testSrc.resolve("org/agentelf/example/ServiceTest.java"),
                "package org.agentelf.example; class ServiceTest {}");

        InitializeReferenceTypeRepo initializer = new InitializeReferenceTypeRepo();
        ReferenceTypeRepo repo = initializer.initializeReferenceTypeRepo(mainSrc.toString(), testSrc.toString());

        // Verify successful indexing
        Optional<String> serviceSource = repo.lookup("Service");
        assertTrue(serviceSource.isPresent(), "Should find Service class");
        assertTrue(serviceSource.get().contains("class Service"), "Source should contain class definition");

        Optional<String> testSource = repo.lookup("ServiceTest");
        assertTrue(testSource.isPresent(), "Should find ServiceTest class");
    }

    @Test
    void testMultipleTypesInSingleFile() throws IOException {
        Path mainSrc = tempDir.resolve("src/main/java");
        Files.createDirectories(mainSrc);
        Files.writeString(mainSrc.resolve("Models.java"),
                "package com.data; class User {} class Order {}");

        InitializeReferenceTypeRepo initializer = new InitializeReferenceTypeRepo();
        ReferenceTypeRepo repo = initializer.initializeReferenceTypeRepo(mainSrc.toString(), null);

        assertTrue(repo.lookup("User").isPresent());
        assertTrue(repo.lookup("Order").isPresent());
        assertEquals(repo.lookup("User").get(), repo.lookup("Order").get(), "Both types should point to the same source file content");
    }

    @Test
    void testDuplicateClassNamesThrowsException() throws IOException {
        // Setup two different packages with the same class name
        Path mainSrc = tempDir.resolve("src/main/java");
        Files.createDirectories(mainSrc.resolve("p1"));
        Files.createDirectories(mainSrc.resolve("p2"));

        Files.writeString(mainSrc.resolve("p1/Common.java"), "package p1; public class Common {}");
        Files.writeString(mainSrc.resolve("p2/Common.java"), "package p2; public class Common {}");

        InitializeReferenceTypeRepo initializer = new InitializeReferenceTypeRepo();
        ReferenceTypeRepo repo = initializer.initializeReferenceTypeRepo(mainSrc.toString(), null);

        // ReferenceTypeRepo.lookup is implemented to throw RuntimeException if not unique
        assertThrows(RuntimeException.class, () -> repo.lookup("Common"), "Lookup should fail for non-unique class names");
    }

    @Test
    void testDefaultPackageSupport() throws IOException {
        Path mainSrc = tempDir.resolve("src/main/java");
        Files.createDirectories(mainSrc);
        Files.writeString(mainSrc.resolve("TopLevel.java"), "public class TopLevel {}");

        InitializeReferenceTypeRepo initializer = new InitializeReferenceTypeRepo();
        ReferenceTypeRepo repo = initializer.initializeReferenceTypeRepo(mainSrc.toString(), "");

        assertTrue(repo.lookup("TopLevel").isPresent());
    }

    @Test
    void testEmptyAndNonJavaFiles() throws IOException {
        Path mainSrc = tempDir.resolve("src/main/java");
        Files.createDirectories(mainSrc);
        Files.writeString(mainSrc.resolve("README.md"), "# Project info");
        Files.writeString(mainSrc.resolve("config.properties"), "key=value");

        InitializeReferenceTypeRepo initializer = new InitializeReferenceTypeRepo();
        ReferenceTypeRepo repo = initializer.initializeReferenceTypeRepo(mainSrc.toString(), null);

        assertNotNull(repo);
        // Note: ReferenceTypeRepo.lookup(name) will NPE on missing keys due to its implementation
        assertThrows(NullPointerException.class, () -> repo.lookup("Missing"));
    }

    @Test
    void testInvalidPaths() {
        InitializeReferenceTypeRepo initializer = new InitializeReferenceTypeRepo();
        
        // Test with null directories
        assertDoesNotThrow(() -> {
            ReferenceTypeRepo repo = initializer.initializeReferenceTypeRepo(null, null);
            assertNotNull(repo);
        });

        // Test with non-existent directory
        assertDoesNotThrow(() -> {
            ReferenceTypeRepo repo = initializer.initializeReferenceTypeRepo("/tmp/path/does/not/exist/agentelf", "");
            assertNotNull(repo);
        });
    }

    @Test
    void testParsingError() throws IOException {
        Path mainSrc = tempDir.resolve("src/main/java");
        Files.createDirectories(mainSrc);
        // Invalid Java syntax
        Files.writeString(mainSrc.resolve("Broken.java"), "class Broken { missing semicolon }");

        InitializeReferenceTypeRepo initializer = new InitializeReferenceTypeRepo();
        
        // StaticJavaParser.parse(Path) throws ParseException inside a RuntimeException in the implementation
        assertThrows(RuntimeException.class, () -> 
            initializer.initializeReferenceTypeRepo(mainSrc.toString(), null)
        );
    }
}