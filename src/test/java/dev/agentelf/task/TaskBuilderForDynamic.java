package dev.agentelf.task;

import dev.agentelf.yaml.TaskBuilder;

import java.util.HashMap;
import java.util.Map;

public class TaskBuilderForDynamic implements TaskBuilder {

    private Map<String,TaskDynamic>  nameToDynamic = new HashMap<>();

    @Override
    public Task create(String name) {
        TaskDynamic taskDynamic = new TaskDynamic();
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
