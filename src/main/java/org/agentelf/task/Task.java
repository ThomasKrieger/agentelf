package org.agentelf.task;

import lombok.Getter;
import org.agentelf.api.RunVariables;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Task {

    public static final String ACTION_LIST_PROPERTY_NAME = "actions";
    private final List<ActionWrapper> actions = new LinkedList<>();

    public void addAction(ActionWrapper actionWrapper) {
        actions.add(actionWrapper);
    }

    public void execute(TaskVariables taskVariables) {
        RunVariables runVariables = taskVariables.toRunVariables();
        for(ActionWrapper actionWrapper : actions) {
            actionWrapper.execute(runVariables);
        }
    }

}
