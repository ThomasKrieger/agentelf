package dev.agentelf.yaml;

import java.util.Deque;

public class StateActionList implements YamlParserState {

    private final TaskOrAction taskOrAction;

    public StateActionList(TaskOrAction taskOrAction) {
        this.taskOrAction = taskOrAction;
    }

    @Override
    public void processScalarEvent(String value, TaskOrActionBuilder builder) {
        taskOrAction.addTask(builder.create(value));
    }

    @Override
    public YamlParserState processMappingStart() {
        return new StateAction(taskOrAction);
    }

    @Override
    public YamlParserState processSequenceStart() {
        throw new RuntimeException("should not happen");
    }
}
