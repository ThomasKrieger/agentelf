package org.agentelf.action;

import org.agentelf.api.Action;
import org.springframework.stereotype.Component;

@Component
public class CallLargeLLM {

    @Action(arguments = {"prompt"},
            returnVariable = "llmResponse")
    public String callLLM(String prompt) {
        return prompt;
    }

}
