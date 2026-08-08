package org.agentelf.cli;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ScanResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Initialize {

    public void initialize() throws IOException {
        File rootDir = new File(".agentelf");
        String[] subDirs = {"context", "task" };
        if (!rootDir.exists()) {
            rootDir.mkdir();
            for (String subDirName : subDirs) {
                File subDir = new File(rootDir, subDirName);
                subDir.mkdir();
                try (ScanResult scanResult = new ClassGraph()
                        .acceptPaths("/org/agentelf/initialresource/" + subDirName)
                        .scan()) {
                    for (Resource resource :
                            scanResult.getAllResources()) {
                        Path target = Paths.get(".agentelf" )
                                .resolve(resource.getPathRelativeToClasspathElement().substring("/org/agentelf/initialresource".length()));
                        try (InputStream in = resource.open()) {
                            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
                        }
                    }
                }
            }
            InputStream in = this.getClass().getResourceAsStream("/org/agentelf/initialresource/config.yml");
            Files.copy(in, Paths.get(".agentelf" ).resolve("config.yml"));
        }
    }

}
