package org.agentelf.cli;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;


class WriteLogbackXMLTest {

    private final WriteLogbackXML writer = new WriteLogbackXML();

    @TempDir
    private Path tempDir;

    @Test
    void testWriteLogbackXML_Success() throws IOException {
        File targetDir = tempDir.resolve("output").toFile();
        
        writer.writeLogbackXML(targetDir);

        File expectedFile = new File(targetDir, "logback.xml");
        assertTrue(expectedFile.exists(), "logback.xml should be created");
        assertTrue(expectedFile.length() > 0, "logback.xml should not be empty");
    }

    @Test
    void testWriteLogbackXML_NullTargetDir_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            writer.writeLogbackXML(null);
        }, "Should throw IllegalArgumentException when targetDir is null");
    }

    @Test
    void testWriteLogbackXML_CreatesNestedDirectories() throws IOException {
        // targetDir does not exist initially
        File nestedDir = tempDir.resolve("level1").resolve("level2").toFile();
        assertFalse(nestedDir.exists());

        writer.writeLogbackXML(nestedDir);

        assertTrue(nestedDir.exists(), "Should create nested directories");
        assertTrue(new File(nestedDir, "logback.xml").exists());
    }

    @Test
    void testWriteLogbackXML_OverwritesExistingFile() throws IOException {
        File targetDir = tempDir.toFile();
        File logbackFile = new File(targetDir, "logback.xml");
        
        // Pre-create the file with dummy content
        String dummyContent = "dummy content";
        Files.writeString(logbackFile.toPath(), dummyContent);
        assertEquals(dummyContent, Files.readString(logbackFile.toPath()));

        writer.writeLogbackXML(targetDir);

        // Verify content is replaced (assuming logbackTemplate.xml is different from "dummy content")
        String newContent = Files.readString(logbackFile.toPath());
        assertNotEquals(dummyContent, newContent, "The existing logback.xml should have been overwritten");
    }

    @Test
    void testWriteLogbackXML_DirectoryCreationFailure_ThrowsException() throws IOException {
        // Create a file where a directory is expected to be created
        File fileAsDir = tempDir.resolve("blocked_path").toFile();
        Files.writeString(fileAsDir.toPath(), "I am a file, not a directory");

        // Attempting to use this file path as the target directory should fail
        assertThrows(IOException.class, () -> {
            writer.writeLogbackXML(fileAsDir);
        }, "Should throw IOException when directory cannot be created");
    }
}