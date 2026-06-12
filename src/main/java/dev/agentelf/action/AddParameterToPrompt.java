package dev.agentelf.action;

import org.springframework.stereotype.Component;

@Component
public class AddParameterToPrompt {

    public String addParameterToPrompt(
        String prompt,
        String firstParameter,
        String secondParameter
    ) {
        return prompt + " " + firstParameter + " " + secondParameter;
    }
}