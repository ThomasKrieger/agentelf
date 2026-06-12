package dev.agentelf.yaml;


import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLFactory;

import java.io.Reader;

public class YamlParser {

    private final ObjectMapper mapper;

    public YamlParser() {
        this.mapper = new ObjectMapper(new YAMLFactory());
    }

    public TaskDescription parse(Reader reader) {
        return mapper.readValue(reader, TaskDescription.class);
    }

}