package dev.agentelf.yaml;

public interface YamlParserState {

    void processScalarEvent(String value,TaskOrActionBuilder builder);
    YamlParserState processMappingStart();

    YamlParserState processSequenceStart();
}
