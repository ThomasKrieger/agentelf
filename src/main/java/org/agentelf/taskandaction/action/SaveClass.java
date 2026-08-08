package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.file.FileOutput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class SaveClass extends AbstractSaveClass{

    private final Path targetDir;
    private final FileOutput fileOutput;

    public SaveClass(FileOutput fileOutput, @Value("${main-target-dir}") Path targetDir) {
        this.fileOutput = fileOutput;
        this.targetDir = targetDir;
    }

    @Action(arguments = {"llmResponse", "className", "packageName" })
    public void saveClass(String llmResponse, String className, String packageName) throws Exception {
        save(llmResponse,className,packageName,fileOutput,targetDir);
    }

}
