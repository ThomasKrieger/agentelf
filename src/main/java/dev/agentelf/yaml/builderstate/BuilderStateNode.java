package dev.agentelf.yaml.builderstate;

import dev.agentelf.task.Task;
import dev.agentelf.yaml.TaskBuilder;

public class BuilderStateNode implements BuilderState {

    private final TaskBuilder taskBuilder;
    private final Task task;


    public BuilderStateNode(TaskBuilder taskBuilder,
                            Task task) {
        this.taskBuilder = taskBuilder;
        this.task = task;
    }

    @Override
    public BuilderState addTask(String name) {
        Task newAction = taskBuilder.create(name);
        task.addTask(newAction);
        return new BuilderStateNode(taskBuilder,newAction);
    }

    @Override
    public BuilderState addProperty(String name, String value) {
        task.addProperty(name,value);
        return this;
    }

    @Override
    public BuilderState addSequence(String name) {
        return new BuilderStateNodeList(taskBuilder, task);
    }

    @Override
    public BuilderState addScalarToSequence(String name) {
        throw new UnsupportedOperationException("Not implemented");
    }

}