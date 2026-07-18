package org.agentelf.taskandaction.task;

import org.agentelf.api.RunVariables;

public interface ActionWrapper {

    void addProperty(String name, String value);
    void execute(RunVariables runVariables);

}
