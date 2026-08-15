package org.agentelf.cli;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class WriteLogbackXML {

    /**
     * Reads logbackTemplate.xml from the class path and writes it as logback.xml to the targetDir.
     * 
     * @param targetDir the directory where logback.xml should be created
     * @throws IOException if the resource is not found or an I/O error occurs
     */
    public void writeLogbackXML(File targetDir) throws IOException {
        if (targetDir == null) {
            throw new IllegalArgumentException("targetDir must not be null");
        }

        if (!targetDir.exists() && !targetDir.mkdirs()) {
            throw new IOException("Could not create directory: " + targetDir.getAbsolutePath());
        }

        try (InputStream in = getClass().getClassLoader().getResourceAsStream("logbackTemplate.xml")) {
            if (in == null) {
                throw new IOException("Resource 'logbackTemplate.xml' not found on the classpath");
            }
            
            File outputFile = new File(targetDir, "logback.xml");
            Files.copy(in, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}