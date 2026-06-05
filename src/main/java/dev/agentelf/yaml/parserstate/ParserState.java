package dev.agentelf.yaml.parserstate;

public interface ParserState {

    ParserState processScalarEvent(String value);
    ParserState processMappingStart();
    ParserState processMappingEnd();
    ParserState processSequenceStart();
    ParserState processSequenceEnd();

}
