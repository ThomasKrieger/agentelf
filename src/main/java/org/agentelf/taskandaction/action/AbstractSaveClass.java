package org.agentelf.taskandaction.action;

import org.agentelf.file.FileOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractSaveClass {

    protected void save(String llmResponse, String className, String packageName, FileOutput fileOutput, Path targetDir) {
        try {
            Path packageDir = targetDir.resolve(packageName.replace('.', File.separatorChar));
            Files.createDirectories(packageDir);
            fileOutput.writeFile(llmResponse,className + ".java",packageDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
