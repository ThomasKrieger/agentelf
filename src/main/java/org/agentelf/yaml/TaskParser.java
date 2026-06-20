package org.agentelf.yaml;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.Reader;

public class TaskParser {

    private final ObjectMapper mapper;

    public TaskParser() {
        this.mapper = new ObjectMapper(new YAMLFactory());
    }

    public TaskDescription parse(Reader reader) throws IOException {
        return mapper.readValue(reader, TaskDescription.class);
    }

}