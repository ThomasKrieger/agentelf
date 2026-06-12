package dev.agentelf.yaml;

import dev.agentelf.task.Task;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskDescription {

    private String name;
    private List<ActionDescription> actions = new ArrayList<>();

    public Task build(TaskAndActionFactory taskAndActionFactory) {
        Task task = taskAndActionFactory.createTask(name);
        for(ActionDescription child : actions) {
            task.addAction(child.build(taskAndActionFactory));
        }
        return task;
    }
}
