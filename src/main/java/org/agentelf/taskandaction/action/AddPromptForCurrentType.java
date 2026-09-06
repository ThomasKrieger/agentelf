package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.SourceModel;
import org.agentelf.mustache.ApplyTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AddPromptForCurrentType {

    private final ApplyTemplate applyTemplate;

    @Action(arguments = {"currentType","sourceModel","prompt"}, returnVariable = "prompt")
    public String addPromptForCurrentType(AbstractTypeSource currentType,
                                          SourceModel sourceModel,
                                          String prompt) throws IOException {
        if(currentType == null) {
            return prompt;
        }
        currentType.setIncludePrompt(true);

        String addToPrompt = sourceModel.createTextForTypesExcept(currentType.getHandle())
                + System.lineSeparator()
                + applyTemplate.applyToIntermediateType(currentType);

        currentType.setIncludePrompt(false);
        return prompt + System.lineSeparator() + addToPrompt;
    }

}
