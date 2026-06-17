package org.agentelf.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadIfFile {

    public String loadIfFile(String potentialFile) {
        try {
            java.nio.file.Path path = Paths.get(potentialFile);
            if (Files.exists(path) && Files.isRegularFile(path)) {
                return Files.readString(path);
            }
        } catch (IOException e) {
            // If an error occurs during reading, return the original string
        }
        return potentialFile;
    }

}