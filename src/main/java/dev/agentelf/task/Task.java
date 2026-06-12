package dev.agentelf.task;

import dev.agentelf.api.RunVariables;
import lombok.Getter;

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
        RunVariables runVariables = new RunVariables();
        runVariables.setFirstParameter(taskVariables.getFirstParameter());
        runVariables.setSecondParameter(taskVariables.getSecondParameter());
        for(ActionWrapper actionWrapper : actions) {
            actionWrapper.execute(runVariables);
        }
    }

}
