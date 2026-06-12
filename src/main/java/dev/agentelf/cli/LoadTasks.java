package dev.agentelf.cli;

import dev.agentelf.yaml.TaskDescription;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoadTasks {

    public void loadTasks() {
        Map<String, TaskDescription> nameToTaskDescription = new HashMap<>();

        String path = ".agentelf" + File.separator + "task";
        File folder = new File(path);
        if (folder.exists() && folder.isDirectory()) {
            File[] yamlFiles = folder.listFiles((dir, name) ->
                            name.toLowerCase().endsWith(".yaml") ||
                            name.toLowerCase().endsWith(".yml"));
            if (yamlFiles != null) {
                for (File file : yamlFiles) {
                    try {
                        new FileReader(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}