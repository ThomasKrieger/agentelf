package org.agentelf.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStateNotExisting implements FileState {

    private final  String fileName;
    private final Path dir;


    public FileStateNotExisting(String fileName, Path dir) {
        this.fileName = fileName;
        this.dir = dir;
    }

    @Override
    public void execute() {
        try {
            Path file = dir.resolve(fileName);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file: " + fileName, e);
        }
    }
}
