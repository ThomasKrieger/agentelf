package org.agentelf.cli;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ScanResult;
import org.agentelf.yaml.TaskDescription;
import org.agentelf.yaml.TaskParser;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoadTasks {

    public Map<String, TaskDescription>  getYamlFilesFromClassPath() throws IOException {
        Map<String, TaskDescription> nameToTaskDescription = new HashMap<>();
       ScanResult scanResult = new ClassGraph()
                .acceptPaths("/org/agentelf/task" )
                .scan();
        for (Resource resource :
                    scanResult.getAllResources()) {
             try(InputStream in = resource.open()) {
                 var description = new TaskParser().parse(new InputStreamReader(in));
                 nameToTaskDescription.put(description.getName(), description);
             }
        }
        return nameToTaskDescription;
    }


}