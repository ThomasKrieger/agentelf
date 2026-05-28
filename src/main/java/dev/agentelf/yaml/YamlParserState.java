package dev.agentelf.yaml;

public interface YamlParserState {

    TaskOrAction taskOrAction();
    void processScalarEvent(String value, TaskOrActionBuilder builder);
    YamlParserState processMappingStart(String value, TaskOrActionBuilder builder);

}
