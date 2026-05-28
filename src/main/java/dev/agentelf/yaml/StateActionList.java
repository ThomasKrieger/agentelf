package dev.agentelf.yaml;

import java.util.Deque;

public class StateActionList implements YamlParserState {

    private final TaskOrAction taskOrAction;
    private TaskOrAction lastAdded;

    public StateActionList(TaskOrAction taskOrAction) {
        this.taskOrAction = taskOrAction;
    }

    @Override
    public TaskOrAction taskOrAction() {
        return taskOrAction;
    }

    @Override
    public void processScalarEvent(String value, TaskOrActionBuilder builder) {
        TaskOrAction newAction = builder.create(value);
        taskOrAction.addTask(newAction);
        lastAdded = newAction;
    }

    @Override
    public YamlParserState processMappingStart(String value, TaskOrActionBuilder builder) {
        return new StateAction(lastAdded);
    }
}
