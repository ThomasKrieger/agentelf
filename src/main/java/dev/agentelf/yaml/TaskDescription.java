package dev.agentelf.yaml;

import dev.agentelf.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDescription {

    private String name;
    private List<TaskDescription> tasks = new ArrayList<>();

    public List<TaskDescription> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDescription> tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task build(TaskFactory taskFactory) {
        Task task = taskFactory.create(name);
        for(TaskDescription child : tasks) {
            task.addTask(child.build(taskFactory));
        }
        return task;
    }
}
