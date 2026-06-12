package dev.agentelf.yaml;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.Reader;

public class YamlParser {

    private final ObjectMapper mapper;

    public YamlParser() {
        this.mapper = new ObjectMapper(new YAMLFactory());
    }

    public TaskDescription parse(Reader reader) throws IOException {
        return mapper.readValue(reader, TaskDescription.class);
    }

}