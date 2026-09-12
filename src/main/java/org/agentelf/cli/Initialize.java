package org.agentelf.cli;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class Initialize {

    public void initialize(boolean clearDirectory) throws IOException {
        log.info("initialize agentelf");
        File rootDir = new File(".agentelf");
        if(clearDirectory) {
            FileUtils.deleteQuietly(rootDir);
        }
        if (!rootDir.exists()) {
            rootDir.mkdir();
            File subDir = new File(rootDir, "context");
            subDir.mkdir();
            InputStream in = this.getClass().getResourceAsStream("/org/agentelf/initialresource/config.yml");
            Files.copy(in, Paths.get(".agentelf").resolve("config.yml"));
            new WriteLogbackXML().writeLogbackXML(new File(".agentelf"));
        }
    }

}
