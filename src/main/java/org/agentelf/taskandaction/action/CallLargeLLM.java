package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.llm.CallLLMList;
import org.springframework.stereotype.Component;

@Component
public class CallLargeLLM {

    private final CallLLMList callLLMList;

    public CallLargeLLM(CallLLMList callLLMList) {
        this.callLLMList = callLLMList;
    }

    @Action(arguments = {"prompt"},
            returnVariable = "llmResponse")
    public String callLLM(String prompt) {
        return callLLMList.large().call(prompt);
    }

}
