package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.model.tosource.AbstractTypeToSource;
import org.agentelf.taskandaction.RunVariables;
import org.agentelf.taskandaction.task.Task;
import org.agentelf.yaml.TaskAndActionFactory;
import org.agentelf.yaml.TaskDescription;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CallSourceModelToSource {

    private final TaskAndActionFactory taskAndActionFactory;

    @Action(arguments = {"intermediateTypeList", "taskMap"})
    public void callIntermediateToSource(List<AbstractTypeToSource> intermediateTypeList,
                                         Map<String, TaskDescription> taskMap) {
        RunVariables runVariables = new RunVariables();
        runVariables.setIntermediateTypeList(intermediateTypeList);
        runVariables.setTaskMap(taskMap);

        TaskDescription taskDescription = taskMap.get("sourceModelToSource");
        Task task = taskDescription.build(taskAndActionFactory);
        task.execute(runVariables);
    }

}
