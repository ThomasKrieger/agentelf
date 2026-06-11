package dev.agentelf.task;

import dev.agentelf.api.Action;

public class ActionGuineaPig {

    @Action( arguments = {"prompt"} , returnVariable = "llmResponse")
    public String callLLM(String prompt) {
        return prompt + " test";
    }

}
