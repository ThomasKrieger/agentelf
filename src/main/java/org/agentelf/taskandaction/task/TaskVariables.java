package org.agentelf.taskandaction.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.agentelf.taskandaction.TaskOrRunVariables;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskVariables extends TaskOrRunVariables {

    private String task;

}
