package org.agentelf.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.Reader;

public class ConfigParser {

    private final ObjectMapper mapper;

    public ConfigParser() {
        this.mapper = new ObjectMapper(new YAMLFactory());
    }

    public ConfigDescription parse(Reader reader) throws IOException {
        return mapper.readValue(reader, ConfigDescription.class);
    }

}
