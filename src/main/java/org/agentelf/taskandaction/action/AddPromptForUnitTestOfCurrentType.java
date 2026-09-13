package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.model.tosource.AbstractTypeToSource;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.mustache.ApplyTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddPromptForUnitTestOfCurrentType {

    private final ApplyTemplate applyTemplate;

    @Action(arguments = {"currentType","toSourceModel","prompt"}, returnVariable = "prompt")
    public String addPromptForUnitTestOfCurrentType(AbstractTypeToSource currentType,
                                                    ToSourceModel toSourceModel,
                                                    String prompt) throws IOException {
        if(currentType == null) {
            return prompt;
        }
        Optional<String> unitTestPrompt = currentType.getUnitTestPrompt(applyTemplate);
        return   prompt +
                  toSourceModel.createTextForAllTypes()
                + System.lineSeparator()
                + unitTestPrompt.get();
    }

}
