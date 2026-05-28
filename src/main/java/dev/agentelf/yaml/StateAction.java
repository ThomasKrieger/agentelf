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
    public void process(String value, Deque<YamlParserState> stack, TaskOrActionBuilder builder) {
        stack.push(new StateKeyValue(taskOrAction,value));
    }
}
