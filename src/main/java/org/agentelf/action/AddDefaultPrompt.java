package org.agentelf.action;

import org.agentelf.api.Action;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AddDefaultPrompt {

    @Value("${default-prompt}")
    private String defaultPrompt;

    @Action(arguments = {"prompt"},
            returnVariable = "prompt")
    public String addDefaultPrompt(String prompt) {
        return  prompt + System.lineSeparator() + defaultPrompt + System.lineSeparator();
    }

}
