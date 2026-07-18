package org.agentelf.yaml;

import org.agentelf.taskandaction.task.ActionWrapper;
import org.agentelf.taskandaction.task.Task;

public interface TaskAndActionFactory {

    Task createTask(String name);
    ActionWrapper createAction(String name);

}
