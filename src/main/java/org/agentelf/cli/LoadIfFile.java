package org.agentelf.cli;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;


public class LoadIfFile {

    private final FileSystem fileSystem;

    public LoadIfFile() {
        this(FileSystems.getDefault());
    }

    LoadIfFile(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public String loadIfFile(String potentialFile) {
        try {
            java.nio.file.Path path = fileSystem.getPath(potentialFile);
            if (Files.exists(path) && Files.isRegularFile(path)) {
                return Files.readString(path);
            }
        } catch (IOException e) {
            // If an error occurs during reading, return the original string
        }
        return potentialFile;
    }

}