package dev.agentelf.yaml;

public interface TaskOrAction extends TaskOrActionParent {

    void addTask(TaskOrAction taskOrAction);
    void setProperty(String propertyName, Object value);
}
