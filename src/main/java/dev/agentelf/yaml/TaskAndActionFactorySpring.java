package dev.agentelf.yaml;

import dev.agentelf.task.ActionWrapper;
import dev.agentelf.task.ActionWrapperBean;
import dev.agentelf.task.Task;
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
