package dev.agentelf.task;

import dev.agentelf.yaml.TaskFactory;

import java.util.HashMap;
import java.util.Map;

public class TaskFactoryForDynamic implements TaskFactory {

    private Map<String,TaskDynamic>  nameToDynamic = new HashMap<>();

    @Override
    public Task create(String name) {
        TaskDynamic taskDynamic = new TaskDynamic(name);
        nameToDynamic.put(name,taskDynamic);
        return taskDynamic;
    }

    public TaskDynamic get(String key) {
        return nameToDynamic.get(key);
    }

    @Override
    public String toString() {
        return "TaskBuilderForDynamic{" +
                "nameToDynamic=" + nameToDynamic +
                '}';
    }
}
