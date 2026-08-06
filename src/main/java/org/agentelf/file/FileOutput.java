package org.agentelf.file;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileOutput {

    private final List<FileState> previousState = new ArrayList<>();
    private final List<FileState> newState = new ArrayList<>();

    public void writeFile(String text, String fileName, Path dir) {
        Path file = dir.resolve(fileName);
        try {
            if (Files.exists(file)) {
                String existingText = Files.readString(
                        file,
                        StandardCharsets.UTF_8
                );
                previousState.add(new FileStateExisting(existingText, fileName, dir));
            } else {
                previousState.add(new FileStateNotExisting(fileName, dir));
            }
            FileStateExisting newOperation = new FileStateExisting(text, fileName, dir);
            newState.add(newOperation);
            newOperation.execute();
        } catch (IOException e) {
            throw new RuntimeException("Failed to process file: " + fileName, e);
        }
    }

    public void undo() {
        for (FileState state : previousState) {
            state.execute();
        }
    }

    public void redo() {
        for (FileState state : newState) {
            state.execute();
        }
    }

}
