package dev.agentelf.yaml.builderstate;

public interface BuilderState {

    BuilderState addTask(String name);
    BuilderState addProperty(String name, String value);
    BuilderState addSequence(String name);
    BuilderState addScalarToSequence(String name);

}
