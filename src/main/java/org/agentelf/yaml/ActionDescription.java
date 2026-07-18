package org.agentelf.yaml;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.agentelf.taskandaction.task.ActionWrapper;

@Data
public class ActionDescription {

    private String name;

    @JsonCreator
    public static ActionDescription fromString(String name) {
        ActionDescription action = new ActionDescription();
        action.setName(name);
        return action;
    }

    public ActionWrapper build(TaskAndActionFactory taskAndActionFactory) {
        return taskAndActionFactory.createAction(name);
    }
}
