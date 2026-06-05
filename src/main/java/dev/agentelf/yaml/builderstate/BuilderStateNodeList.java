package dev.agentelf.yaml.builderstate;

import dev.agentelf.task.Task;
import dev.agentelf.yaml.TaskBuilder;

public class BuilderStateNodeList implements BuilderState {

    private final TaskBuilder taskBuilder;
    private final Task task;

    public BuilderStateNodeList(TaskBuilder taskBuilder,
                                Task task) {
        this.taskBuilder = taskBuilder;
        this.task = task;
    }

    @Override
    public BuilderState addTask(String name) {
        Task action = taskBuilder.create(name);
        task.addTask(action);
        return new BuilderStateNode(taskBuilder,action);
    }

    @Override
    public BuilderState addProperty(String name, String value) {
        return this;
    }

    @Override
    public BuilderState addSequence(String name) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public BuilderState addScalarToSequence(String name) {
        Task action = taskBuilder.create(name);
        task.addTask(action);
        return this;
    }
}