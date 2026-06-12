package dev.agentelf.task;

import dev.agentelf.api.RunVariables;

public interface ActionWrapper {

    void addProperty(String name, String value);
    void execute(RunVariables runVariables);

}
