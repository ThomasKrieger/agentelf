package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.model.intermediate.AbstractTypeIntermediate;
import org.agentelf.mustache.ApplyTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddImplementMethodPromptToIntermediateModel {

    private final ApplyTemplate applyTemplate;

    @Action(arguments = {"intermediateTypeList"})
    public void addImplementMethodPromptToIntermediateModel(List<AbstractTypeIntermediate> abstractTypeList) {
        abstractTypeList
                .stream()
                .flatMap( model -> model.getDeclaredMethods().stream())
                .forEach( method -> method.addImplementMethodToPrompt(applyTemplate));
    }

}
