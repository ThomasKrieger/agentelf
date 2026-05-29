package dev.agentelf.yaml;

public class StateAction  implements YamlParserState {

    private final TaskOrActionParent parent;
    private TaskOrAction taskOrAction;

    public StateAction(TaskOrActionParent parent) {
        this.parent = parent;
    }

    @Override
    public void processScalarEvent(String value, TaskOrActionBuilder builder) {
        if(taskOrAction == null) {
            taskOrAction = builder.create(value);
            parent.addTask(taskOrAction);
        } else {
            throw new RuntimeException("should not happen");
        }
    }

    @Override
    public YamlParserState processMappingStart() {
        return new StateKeyValue(taskOrAction);
    }

    @Override
    public YamlParserState processSequenceStart() {
        return new StateActionList(taskOrAction);
    }
}
