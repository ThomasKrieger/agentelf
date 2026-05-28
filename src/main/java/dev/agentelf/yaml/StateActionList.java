package dev.agentelf.yaml;

import java.util.Deque;

public class StateActionList implements YamlParserState {

    private final TaskOrAction taskOrAction;

    public StateActionList(TaskOrAction taskOrAction) {
        this.taskOrAction = taskOrAction;
    }

    @Override
    public TaskOrAction taskOrAction() {
        return taskOrAction;
    }

    @Override
    public void process(String value, Deque<YamlParserState> stack, TaskOrActionBuilder builder) {
        TaskOrAction newAction = builder.create(value);
        taskOrAction.addTask(newAction);
        stack.push(new StateAction(newAction));
    }
}
