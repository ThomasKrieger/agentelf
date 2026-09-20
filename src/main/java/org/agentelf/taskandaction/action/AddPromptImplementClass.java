package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.mustache.ApplyTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AddPromptImplementClass {

    private final ApplyTemplate applyTemplate;

    @Action(arguments = {"className","prompt"}, returnVariable = "prompt")
    public String addPromptImplementClass(String className, String prompt) throws IOException {
        Map<String,Object> context = new HashMap<>();
        context.put("name",className);
        String addToPrompt = applyTemplate.apply(context,"promptImplementClass.mustache");
        return  prompt + System.lineSeparator() + addToPrompt;
    }

}
