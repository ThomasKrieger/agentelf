package org.agentelf.cli;

import org.agentelf.yaml.TaskDescription;
import org.agentelf.yaml.YamlParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoadTasks {

    public Map<String, TaskDescription> loadTasks() {
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
                       var description =  new YamlParser().parse(new FileReader(file));
                       nameToTaskDescription.put(description.getName(),description);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return nameToTaskDescription;
    }

}