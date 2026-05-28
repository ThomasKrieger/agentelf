package dev.agentelf.yaml;

import java.util.Deque;

public class StateKeyValue implements YamlParserState {

    private final TaskOrAction taskOrAction;
    private final String key;

    public StateKeyValue(TaskOrAction taskOrAction,
                         String key) {
        this.taskOrAction = taskOrAction;
        this.key = key;
    }

    @Override
    public TaskOrAction taskOrAction() {
        throw new RuntimeException("should not called");
    }

    @Override
    public void processScalarEvent(String value, TaskOrActionBuilder builder) {
        taskOrAction.setProperty(key,value);
    }

    @Override
    public YamlParserState processMappingStart(String value, TaskOrActionBuilder builder) {
        return this;
    }
}
