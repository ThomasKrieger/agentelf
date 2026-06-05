package dev.agentelf.yaml.parserstate;

import dev.agentelf.yaml.builderstate.BuilderState;

public class ParserStateInitial implements ParserState {

    private BuilderState builderState;
    private final ParserState previous;

    public ParserStateInitial(BuilderState builderState, ParserState previous) {
        this.builderState = builderState;
        this.previous = previous;
    }

    @Override
    public ParserState processScalarEvent(String value) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ParserState processMappingStart() {
        return new ParserStateMappingStarted(builderState, this);
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
