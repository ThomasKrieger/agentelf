package dev.agentelf.yaml;

import java.util.Deque;

public class StateKeyValue implements YamlParserState {

    private final TaskOrAction taskOrAction;
    private String key = null;

    public StateKeyValue(TaskOrAction taskOrAction) {
        this.taskOrAction = taskOrAction;
    }

    @Override
    public void processScalarEvent(String value, TaskOrActionBuilder builder) {
        if(key == null) {
            key = value;
        } else {
            taskOrAction.setProperty(key,value);
        }
    }

    @Override
    public YamlParserState processMappingStart() {
        throw new RuntimeException("should not happen");
    }

    @Override
    public YamlParserState processSequenceStart() {
        return new StateActionList(taskOrAction);
    }
}
