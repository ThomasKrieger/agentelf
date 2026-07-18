package org.agentelf.yaml;

import org.agentelf.taskandaction.task.ActionWrapper;
import org.agentelf.taskandaction.task.ActionWrapperBean;
import org.agentelf.taskandaction.task.Task;
import org.springframework.context.ApplicationContext;

public class TaskAndActionFactorySpring implements TaskAndActionFactory  {

    private final ApplicationContext ctx;

    public TaskAndActionFactorySpring(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public Task createTask(String name) {
        return new Task();
    }

    @Override
    public ActionWrapper createAction(String name) {
        return new ActionWrapperBean(ctx.getBean(name));
    }
}
