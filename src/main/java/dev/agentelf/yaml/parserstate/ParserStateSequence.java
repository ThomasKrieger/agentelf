package dev.agentelf.yaml.parserstate;

import dev.agentelf.yaml.builderstate.BuilderState;

public class ParserStateSequence implements ParserState {

    private BuilderState builderState;
    private final ParserState previous;

    public ParserStateSequence(BuilderState builderState, ParserState previous) {
        this.builderState = builderState;
        this.previous = previous;
    }

    @Override
    public ParserState processScalarEvent(String value) {
        builderState = builderState.addScalarToSequence(value);
        return this;
    }

    @Override
    public ParserState processMappingStart() {
        return new ParserStateMappingStarted(builderState,this);
    }

    @Override
    public ParserState processMappingEnd() {
        return this;
    }

    @Override
    public ParserState processSequenceStart() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ParserState processSequenceEnd() {
        return previous;
    }
}