package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.springframework.stereotype.Component;

@Component
public class SetCreatedClass {

    @Action(arguments = {"llmResponse"},
            returnVariable = "createdClass")
    public String setCreatedClass(String llmResponse) {
        return llmResponse;
    }


}
