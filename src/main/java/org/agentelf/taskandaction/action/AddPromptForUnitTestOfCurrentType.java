package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.SourceModel;
import org.agentelf.mustache.ApplyTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddPromptForUnitTestOfCurrentType {

    private final ApplyTemplate applyTemplate;

    @Action(arguments = {"currentType","sourceModel","prompt"}, returnVariable = "prompt")
    public String addPromptForUnitTestOfCurrentType(AbstractTypeSource currentType,
                                          SourceModel sourceModel,
                                          String prompt) throws IOException {
        if(currentType == null) {
            return prompt;
        }
        Optional<String> unitTestPrompt = currentType.getUnitTestPrompt(applyTemplate);
        return   prompt +
                  sourceModel.createTextForAllTypes()
                + System.lineSeparator()
                + unitTestPrompt.get();
    }

}
