package org.agentelf.yaml;

import org.agentelf.taskandaction.task.ActionWrapper;
import org.agentelf.taskandaction.task.ActionWrapperBean;
import org.agentelf.taskandaction.task.Task;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskAndActionFactorySpring implements TaskAndActionFactory  {

    private final BeanFactory beanFactory;

    public TaskAndActionFactorySpring(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Task createTask(String name) {
        return new Task();
    }

    @Override
    public ActionWrapper createAction(String name) {
        return new ActionWrapperBean(beanFactory.getBean(name));
    }
}
