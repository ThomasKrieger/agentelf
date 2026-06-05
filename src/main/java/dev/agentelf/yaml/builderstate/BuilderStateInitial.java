package dev.agentelf.yaml.builderstate;

import dev.agentelf.task.Task;
import dev.agentelf.yaml.TaskBuilder;
import dev.agentelf.yaml.ParserStateParent;

public class BuilderStateInitial implements BuilderState {

    private final TaskBuilder taskBuilder;
    private final ParserStateParent parserStateParent;

    public BuilderStateInitial(TaskBuilder taskBuilder,
                               ParserStateParent parserStateParent) {
        this.taskBuilder = taskBuilder;
        this.parserStateParent = parserStateParent;
    }


    @Override
    public BuilderState addTask(String name) {
        Task action = taskBuilder.create(name);
        parserStateParent.setActionWrapper(action);
        return new BuilderStateNode(taskBuilder,action);
    }

    @Override
    public BuilderState addProperty(String name, String value) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public BuilderState addSequence(String name) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public BuilderState addScalarToSequence(String name) {
        throw new UnsupportedOperationException("Not implemented");
    }


}