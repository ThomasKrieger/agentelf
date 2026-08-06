package org.agentelf.cli;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ScanResult;
import org.agentelf.yaml.TaskDescription;
import org.agentelf.yaml.TaskParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadTasks {

    public Reader[] getYamlFilesFromConfigDir() throws FileNotFoundException {
        String path = ".agentelf" + File.separator + "task";
        File folder = new File(path);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) ->
                            name.toLowerCase().endsWith(".yaml") ||
                            name.toLowerCase().endsWith(".yml"));
            Reader[] readers = new Reader[files.length];
            for(int index = 0; index < files.length; index++) {
                readers[index] = new FileReader(files[index]);
            }
            return readers;
        }
        throw new RuntimeException(path + "does not exist");
    }

    public Reader[] getYamlFilesFromClassPath() throws IOException {
        List<Reader> readerList = new ArrayList<>();
       ScanResult scanResult = new ClassGraph()
                .acceptPaths("/org/agentelf/initialresource/task" )
                .scan();
        for (Resource resource :
                    scanResult.getAllResources()) {
             InputStream in = resource.open();
             readerList.add(new InputStreamReader(in));
        }
        return readerList.toArray(new Reader[0]);
    }

    public Map<String, TaskDescription> loadTasks(Reader[] yamlFiles ) throws IOException {
        Map<String, TaskDescription> nameToTaskDescription = new HashMap<>();
        for (Reader reader : yamlFiles) {
            try {
                var description =  new TaskParser().parse(reader);
                nameToTaskDescription.put(description.getName(),description);
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally{
                reader.close();
            }
        }
        return nameToTaskDescription;
    }


}