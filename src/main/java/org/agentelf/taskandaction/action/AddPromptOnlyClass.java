package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AddPromptOnlyClass extends AddPromptAbstract{

    @Value("${prompt-only-class}")
    private String promptOnlyClass;

    @Action(arguments = {"prompt"},
            returnVariable = "prompt")
    public String addPromptOnlyClass(String prompt) {
        return  addPrompt(prompt,promptOnlyClass);
    }

}
