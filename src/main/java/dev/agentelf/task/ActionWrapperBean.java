package dev.agentelf.task;

import dev.agentelf.api.Action;
import dev.agentelf.api.RunVariables;
import org.apache.commons.beanutils2.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class ActionWrapperBean implements ActionWrapper {

    private final Object bean;

    public ActionWrapperBean(Object bean) {
        this.bean = bean;
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
