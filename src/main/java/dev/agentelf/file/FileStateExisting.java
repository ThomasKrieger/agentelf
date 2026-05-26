package dev.agentelf.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStateExisting implements FileState {

    private final String text;
    private final  String fileName;
    private final Path dir;

    public FileStateExisting(String text, String fileName, Path dir) {
        this.text = text;
        this.fileName = fileName;
        this.dir = dir;
    }

    @Override
    public void execute() {
        try {
            // Create directory structure if it does not exist
            Files.createDirectories(dir);
            Path file = dir.resolve(fileName);
            Files.writeString(
                    file,
                    text,
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + fileName, e);
        }
    }

}
