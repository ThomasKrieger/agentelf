package org.agentelf.task;

import lombok.Data;
import org.agentelf.api.RunVariables;

import java.util.List;

@Data
public class TaskVariables {

    private String parameter;
    private String packageName;
    private String className;
    private List<String> uses;

    public RunVariables toRunVariables() {
        RunVariables runVariables = new RunVariables();
        runVariables.setParameter(parameter);
        runVariables.setPackageName(packageName);
        runVariables.setClassName(className);
        runVariables.setUses(uses);
        return runVariables;
    }

}
