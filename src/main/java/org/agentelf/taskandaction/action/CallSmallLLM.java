package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.llm.CallLLMList;
import org.springframework.stereotype.Component;

@Component
public class CallSmallLLM {

    private final CallLLMList callLLMList;

    public CallSmallLLM(CallLLMList callLLMList) {
        this.callLLMList = callLLMList;
    }

    @Action(arguments = {"prompt"},
            returnVariable = "llmResponse")
    public String callLLM(String prompt) {
        return callLLMList.callSmall(prompt);
    }

}
