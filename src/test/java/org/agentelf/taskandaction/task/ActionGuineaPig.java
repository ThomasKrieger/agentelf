package org.agentelf.taskandaction.task;

import org.agentelf.api.Action;

public class ActionGuineaPig {

    @Action( arguments = {"prompt"} , returnVariable = "llmResponse")
    public String callLLM(String prompt) {
        return prompt + " test";
    }

}
