package dev.agentelf.yaml.parserstate;

import dev.agentelf.yaml.builderstate.BuilderState;

public class ParserStateMapped implements ParserState {

    private BuilderState builderState;
    private final ParserState previous;
    private final String key;

    public ParserStateMapped(BuilderState builderState,
                             ParserState previous,
                             String key) {
        this.builderState = builderState;
        this.previous = previous;
        this.key = key;
    }

    @Override
    public ParserState processScalarEvent(String value) {
        builderState = builderState.addProperty(key,value);
        return previous;
    }

    @Override
    public ParserState processMappingStart() {
        builderState = builderState.addTask(key);
        return new ParserStateMappingStarted(builderState.addTask(key), this);
    }

    @Override
    public ParserState processSequenceStart() {
        return new ParserStateSequence(builderState.addSequence(key), this);
    }

    @Override
    public ParserState processMappingEnd() {
        builderState = builderState.addTask(key);
       return previous;
    }

    @Override
    public ParserState processSequenceEnd() {
        throw new RuntimeException("Not implemented");
    }
}