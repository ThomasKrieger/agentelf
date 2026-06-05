package dev.agentelf.task;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A Map backed implementation of Task used for testing
 */
public class TaskDynamic implements Task {

    private final Map<String,String> properties = new HashMap<>();
    private final List<Task> tasks = new LinkedList<>();

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void addProperty(String name, String value) {
        properties.put(name,value);
    }

    @Override
    public String toString() {
        return "TaskDynamic{" +
                "properties=" + properties +
                ", tasks=" + tasks +
                '}';
    }
}
