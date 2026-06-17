package org.agentelf.yaml;

import org.agentelf.task.ActionWrapper;
import org.agentelf.task.Task;

public interface TaskAndActionFactory {

    Task createTask(String name);
    ActionWrapper createAction(String name);

}
