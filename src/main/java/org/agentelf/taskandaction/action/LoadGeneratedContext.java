package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.agentelf.taskandaction.action.LoadContext.loadFromDir;

@Component
public class LoadGeneratedContext {

    @Action(arguments = {"prompt"},
            returnVariable = "prompt")
    public String loadGeneratedContext(String prompt) {
        Path contextDir = Paths.get("generated", "context");
        if(Files.exists(contextDir)) {
            return loadFromDir(prompt,contextDir);
        }
       return prompt;
    }

}
