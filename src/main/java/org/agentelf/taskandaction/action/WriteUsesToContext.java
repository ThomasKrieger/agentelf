package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

@Component
public class WriteUsesToContext {

    /**
     * iterates over all elements of uses.
     * Each String represents an absolute path
     * If it is a file copy it to workingdir/generated/context
     * if it is a directory copy all files to workingdir/generated/context
     * if workingdir/generated/context does not exist it generates it.
     */
    @Action(arguments = {"uses"})
    public void writeUsesToContext(List<String> uses) {
        Path targetDir = Paths.get("generated", "context");

        try {
            if (!Files.exists(targetDir)) {
                Files.createDirectories(targetDir);
            }
            for (String use : uses) {
                Path sourcePath = Paths.get(use);
                if (!Files.exists(sourcePath)) {
                    continue;
                }
                if (Files.isDirectory(sourcePath)) {
                    Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            Files.copy(file, targetDir.resolve(sourcePath.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                            return FileVisitResult.CONTINUE;
                        }
                    });
                } else {
                    Files.copy(sourcePath, targetDir.resolve(sourcePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to copy files to context directory", e);
        }
    }
}