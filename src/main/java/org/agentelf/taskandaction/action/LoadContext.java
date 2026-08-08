package org.agentelf.taskandaction.action;


import org.agentelf.api.Action;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * LoadContext loads all files from the contextDir
 *  adds them to the prompt variable and returns the new prompt
 */
@Data
@Component
public class LoadContext {

    @Value("${context-dir}")
    private Path contextDir;

    @Action(arguments = {"prompt"},
            returnVariable = "prompt")
    public String loadContext(String prompt) throws Exception {
       return loadFromDir(prompt,contextDir);
    }

    public static String loadFromDir(String prompt, Path dir) throws Exception{
        if (dir == null || !Files.exists(dir) || !Files.isDirectory(dir)) {
            return prompt;
        }
        try (Stream<Path> paths = Files.walk(dir)) {
            String filesContent = paths
                    .filter(Files::isRegularFile)
                    .map(path -> {
                        try {
                            return Files.readString(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.joining(System.lineSeparator()));
            return prompt + System.lineSeparator() + filesContent;
        }
    }

}