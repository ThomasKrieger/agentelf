package dev.agentelf.yaml.parserstate;

import dev.agentelf.yaml.builderstate.BuilderState;

public class ParserStateMappingStarted implements ParserState {

    private BuilderState builderState;
    private final ParserState previous;

    public ParserStateMappingStarted(BuilderState builderState, ParserState previous) {
        this.builderState = builderState;
        this.previous = previous;
    }

    @Override
    public ParserState processScalarEvent(String value) {
        return new ParserStateMapped(builderState, this , value);
    }

    @Override
    public ParserState processMappingStart() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ParserState processMappingEnd() {
        return previous;
    }

    @Override
    public ParserState processSequenceStart() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ParserState processSequenceEnd() {
        return previous;
    }
}