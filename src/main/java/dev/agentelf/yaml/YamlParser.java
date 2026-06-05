package dev.agentelf.yaml;


import dev.agentelf.task.Task;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLFactory;


import java.io.Reader;

public class YamlParser {

    private final TaskFactory builder;
    private final ObjectMapper mapper;

    public YamlParser(TaskFactory builder) {
        this.mapper = new ObjectMapper(new YAMLFactory());
        this.builder = builder;
    }

    public Task parse(Reader reader) {
        TaskDescription taskDescription = mapper.readValue(reader, TaskDescription.class);
        return taskDescription.build(builder);
    }

}