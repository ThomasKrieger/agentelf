package org.agentelf.action;

import org.agentelf.api.Action;
import org.springframework.stereotype.Component;

@Component
public class AddParameterToPrompt {

    @Action(arguments = {"prompt","firstParameter","secondParameter"},
            returnVariable = "prompt")
    public String addParameterToPrompt(
        String prompt,
        String firstParameter,
        String secondParameter
    ) {
        return prompt + System.lineSeparator()
                + replaceNullWithBlank(firstParameter)
                + System.lineSeparator()
                + replaceNullWithBlank(secondParameter);
    }

    private String replaceNullWithBlank(String string) {
        if(string == null) {
            return "";
        }
        return string;
    }
}