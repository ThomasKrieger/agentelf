package dev.agentelf.action;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * LoadContext loads all files from the contextFolder
 *  adds them to the prompt variable and returns the new prompt
 */

@Data
@Component
public class LoadContext {

    private Path contextFolder;

    public String loadContext(String prompt) {
        if (contextFolder == null || !Files.exists(contextFolder) || !Files.isDirectory(contextFolder)) {
            return prompt;
        }
        try (Stream<Path> paths = Files.walk(contextFolder)) {
            String filesContent = paths
                    .filter(Files::isRegularFile)
                    .map(path -> {
                        try {
                            return Files.readString(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.joining("\n"));
            return prompt + "\n" + filesContent;
        } catch (IOException e) {
            return prompt;
        }
    }
}