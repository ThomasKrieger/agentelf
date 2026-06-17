package org.agentelf.action;

import org.agentelf.api.Action;
import org.springframework.stereotype.Component;

@Component
public class SaveClass {

    @Action(arguments = {"llmResponse"})
    public void callLLM(String llmResponse) {

    }

}
