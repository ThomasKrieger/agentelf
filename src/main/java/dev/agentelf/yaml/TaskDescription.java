package dev.agentelf.yaml;

import dev.agentelf.task.Task;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskDescription {

    private String name;
    private List<TaskDescription> tasks = new ArrayList<>();

    public Task build(TaskFactory taskFactory) {
        Task task = taskFactory.create(name);
        for(TaskDescription child : tasks) {
            task.addTask(child.build(taskFactory));
        }
        return task;
    }
}
