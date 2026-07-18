package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.springframework.stereotype.Component;

@Component
public class AddParameterToPrompt {

    @Action(arguments = {"prompt","parameter"},
            returnVariable = "prompt")
    public String addParameterToPrompt(String prompt, String parameter) {
        return prompt + System.lineSeparator()
                + replaceNullWithBlank(parameter)
                + System.lineSeparator();
    }

    private String replaceNullWithBlank(String string) {
        if(string == null) {
            return "";
        }
        return string;
    }
}