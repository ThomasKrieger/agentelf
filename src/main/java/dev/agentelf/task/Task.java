package dev.agentelf.task;

import dev.agentelf.api.RunVariables;

public interface Task {

    void addTask(Task task);
    void addProperty(String name, String value);
    void execute(RunVariables runVariables);

}
