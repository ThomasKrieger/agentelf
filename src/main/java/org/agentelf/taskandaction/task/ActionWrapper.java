package org.agentelf.taskandaction.task;

import org.agentelf.taskandaction.RunVariables;

public interface ActionWrapper {

    void addProperty(String name, String value);
    void execute(RunVariables runVariables);

}
