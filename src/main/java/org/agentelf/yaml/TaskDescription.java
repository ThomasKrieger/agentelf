package org.agentelf.yaml;

import org.agentelf.task.Task;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskDescription {

    private String name;
    private List<ActionDescription> actions = new ArrayList<>();
    private List<String> profiles = new ArrayList<>();

    public Task build(TaskAndActionFactory taskAndActionFactory) {
        Task task = taskAndActionFactory.createTask(name);
        for(ActionDescription child : actions) {
            task.addAction(child.build(taskAndActionFactory));
        }
        return task;
    }
}
