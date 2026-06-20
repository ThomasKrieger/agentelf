package org.agentelf.task;

import lombok.Data;
import org.agentelf.api.RunVariables;

@Data
public class TaskVariables {

    private String parameter;
    private String packageName;
    private String className;

    public RunVariables toRunVariables() {
        RunVariables runVariables = new RunVariables();
        runVariables.setParameter(parameter);
        runVariables.setPackageName(packageName);
        runVariables.setClassName(className);
        return runVariables;
    }

}
