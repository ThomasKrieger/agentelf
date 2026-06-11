package dev.agentelf.task;

import dev.agentelf.api.Action;
import dev.agentelf.api.RunVariables;
import org.apache.commons.beanutils2.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TaskBean implements Task {

    private static final String TASK_LIST_PROPERTY_NAME = "tasks";

    private final Object bean;

    public TaskBean(Object bean) {
        this.bean = bean;
    }

    public void addTask(Task task) {
        try {
            Object actionsObj = PropertyUtils.getProperty(bean, TASK_LIST_PROPERTY_NAME);
            Collection<Object> actions;
            if (actionsObj == null) {
                actions = new ArrayList<>();
                PropertyUtils.setProperty(bean, TASK_LIST_PROPERTY_NAME, actions);
            } else if (actionsObj instanceof Collection<?> collection) {
                actions = (Collection<Object>) collection;
            } else {
                throw new IllegalStateException(
                        "'tasks' property is not a Collection on " + bean.getClass().getName());
            }
            actions.add(task);
        } catch (IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException("Failed to add action", e);
        }
    }

    @Override
    public void addProperty(String name, String value) {
        try {
            PropertyUtils.setProperty(bean, name, value);
        } catch (IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(
                    "Failed to set property '" + name + "' on " + bean.getClass().getName(), e);
        }
    }

    @Override
    public void execute(RunVariables runVariables) {
        try {
            for (Method method : bean.getClass().getMethods()) {
                Action annotation = method.getAnnotation(Action.class);
                if (annotation != null) {
                    List<Object> argumentList = new LinkedList<>();
                    for (String variableName : annotation.arguments()) {
                        argumentList.add(PropertyUtils.getProperty(runVariables, variableName));
                    }
                    Object result = method.invoke(bean,argumentList.toArray());
                    if(! annotation.returnVariable().isEmpty()) {
                        PropertyUtils.setProperty(runVariables, annotation.returnVariable(), result);
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getBean() {
        return bean;
    }
}
