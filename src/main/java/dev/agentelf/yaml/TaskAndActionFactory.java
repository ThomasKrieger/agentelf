package dev.agentelf.yaml;

import dev.agentelf.task.ActionWrapper;
import dev.agentelf.task.Task;

public interface TaskAndActionFactory {

    Task createTask(String name);
    ActionWrapper createAction(String name);

}
