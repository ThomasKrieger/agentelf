package dev.agentelf.yaml;

import dev.agentelf.task.Task;

public interface TaskFactory {

    Task create(String name);

}
