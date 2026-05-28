package dev.agentelf.yaml;

public interface TaskOrAction {

    void addTask(TaskOrAction taskOrAction);
    void setProperty(String propertyName, Object value);
}
