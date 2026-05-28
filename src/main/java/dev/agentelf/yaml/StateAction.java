package dev.agentelf.yaml;

import java.util.Deque;

public class StateAction  implements YamlParserState {

    private final TaskOrAction taskOrAction;

    public StateAction(TaskOrAction taskOrAction) {
        this.taskOrAction = taskOrAction;
    }

    @Override
    public TaskOrAction taskOrAction() {
        return taskOrAction;
    }

    @Override
    public void processScalarEvent(String value, TaskOrActionBuilder builder) {

    }

    @Override
    public YamlParserState processMappingStart(String value, TaskOrActionBuilder builder) {
        return new StateKeyValue(taskOrAction,value);
    }

}
