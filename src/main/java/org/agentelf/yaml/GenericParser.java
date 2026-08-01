package org.agentelf.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.Reader;

public class GenericParser<RESULT> {

    private final ObjectMapper mapper;
    private final Class<RESULT> classOfResult;

    public GenericParser(Class<RESULT> classOfResult) {
        this.classOfResult = classOfResult;
        this.mapper = new ObjectMapper(new YAMLFactory());
    }

    public RESULT parse(Reader reader) throws IOException {
        return mapper.readValue(reader, classOfResult);
    }

}