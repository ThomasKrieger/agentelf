package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.intermediate.AbstractTypeIntermediate;
import org.agentelf.model.intermediate.MethodIntermediate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddImplementMethodPromptToIntermediateModel {

    @Action(arguments = {"intermediateTypeList"})
    public void addImplementMethodPromptToIntermediateModel(List<AbstractTypeIntermediate> abstractTypeList) {
        abstractTypeList
                .stream()
                .flatMap( model -> model.getDeclaredMethods().stream())
                .forEach(MethodIntermediate::addImplementMethodToPrompt);
    }

}
