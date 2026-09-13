package org.agentelf.taskandaction.action;

import lombok.Data;
import org.agentelf.api.Action;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Data
@Component
public class LoadClass {

    @Value("${main-target-dir}")
    private String targetDir;

    /**
     * loads the file given by className and packageName from the targetDir and
     * adds it as text to the prompt
     */
    @Action(arguments = {"packageName", "className", "prompt"}, returnVariable = "prompt")
    public String loadClass(String packageName, String className, String prompt) throws IOException {
        String resultPrompt = (prompt == null) ? "" : prompt;
            // Convert the package name dots to path separators
            String packagePath = packageName.replace('.', '/');
            Path filePath = Paths.get(targetDir, packagePath, className + ".java");
            if (Files.exists(filePath)) {
                String classContent = Files.readString(filePath);
                StringBuilder sb = new StringBuilder(resultPrompt);
                if (!sb.isEmpty()) {
                    sb.append("\n\n");
                }
                sb.append("Source code for ").append(packageName).append(".").append(className).append(":\n");
                sb.append("```java\n");
                sb.append(classContent);
                sb.append("\n```");
                return sb.toString();
            } else {
                throw new RuntimeException("[Error: Source file for " + packageName + "." + className + " not found at " + filePath.toAbsolutePath() + "]");
            }
        }

}