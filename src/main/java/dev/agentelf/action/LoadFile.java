package dev.agentelf.action;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

@Component
public class LoadFile {

    private Path path;

    public LoadFile(Path path) {
        this.path = path;
    }

    public String loadFile(String prompt) {
        try {
            String fileContent = Files.readString(this.path);
            return prompt + fileContent;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file at: " + path, e);
        }
    }
}