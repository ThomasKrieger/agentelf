package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.file.FileOutput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class SaveClass {

    @Value("${target-dir}")
    private Path targetDir;

    @Action(arguments = {"llmResponse", "className", "packageName", "fileOutput"  })
    public void saveClass(String llmResponse, String className, String packageName, FileOutput fileOutput) {
        try {
            Path packageDir = targetDir.resolve(packageName.replace('.', File.separatorChar));
            Files.createDirectories(packageDir);
            fileOutput.writeFile(llmResponse,className + ".java",packageDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
