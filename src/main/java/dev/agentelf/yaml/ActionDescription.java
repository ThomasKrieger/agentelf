package dev.agentelf.yaml;

import dev.agentelf.task.ActionWrapper;
import lombok.Data;

@Data
public class ActionDescription {

    private String name;

    public ActionWrapper build(TaskAndActionFactory taskAndActionFactory) {
        return taskAndActionFactory.createAction(name);
    }
}
