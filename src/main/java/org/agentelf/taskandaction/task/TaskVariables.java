package org.agentelf.taskandaction.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.agentelf.taskandaction.RunVariables;

@Data
public class TaskVariables {

    private String task;
    private String prompt;
    @JsonProperty("package")
    private String packageName = "";
    @JsonProperty("class")
    private String className;

    public RunVariables toRunVariables() {
        RunVariables runVariables = new RunVariables();
        runVariables.setPrompt(prompt);
        runVariables.setPackageName(packageName);
        runVariables.setClassName(className);
        return runVariables;
    }

}
