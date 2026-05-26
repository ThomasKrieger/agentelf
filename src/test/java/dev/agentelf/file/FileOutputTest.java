package dev.agentelf.file;

import org.junit.jupiter.api.Test;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileOutputTest {

    @Test
    void shouldWriteUndoAndRedoFile() throws Exception {

        try (FileSystem fs = Jimfs.newFileSystem(Configuration.unix())) {
            Path dir = fs.getPath("/test");
            Files.createDirectories(dir);
            Path file = dir.resolve("example.txt");
            // existing file
            Files.writeString(file, "old-content");
            FileOutput output = new FileOutput();

            // overwrite file
            output.writeFile(
                    "new-content",
                    "example.txt",
                    dir
            );

            // verify new content
            assertEquals(
                    "new-content",
                    Files.readString(file)
            );

            // undo -> restore old content
            output.undo();
            assertEquals(
                    "old-content",
                    Files.readString(file)
            );

            // redo -> restore new content
            output.redo();
            assertEquals(
                    "new-content",
                    Files.readString(file)
            );
        }
    }

    @Test
    void shouldUndoCreatedFileByDeletingIt() throws Exception {

        try (FileSystem fs = Jimfs.newFileSystem(Configuration.unix())) {
            Path dir = fs.getPath("/test");
            Files.createDirectories(dir);
            Path file = dir.resolve("created.txt");

            FileOutput output = new FileOutput();

            // create new file
            output.writeFile(
                    "created-content",
                    "created.txt",
                    dir
            );

            assertTrue(
                    Files.exists(file)
            );

            assertEquals(
                    "created-content",
                    Files.readString(file)
            );

            // undo -> delete file
            output.undo();
            assertFalse(
                    Files.exists(file)
            );

            // redo -> recreate file
            output.redo();

            assertTrue(
                    Files.exists(file)
            );
            assertEquals(
                    "created-content",
                    Files.readString(file)
            );
        }
    }
}
