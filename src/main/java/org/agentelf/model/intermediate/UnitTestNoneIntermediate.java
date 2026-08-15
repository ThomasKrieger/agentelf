package org.agentelf.model.intermediate;

import org.agentelf.mustache.ApplyTemplate;

import java.util.Optional;

public class UnitTestNoneIntermediate implements UnitTestIntermediate{
    @Override
    public Optional<String> getPrompt(ApplyTemplate applyTemplate, AbstractTypeIntermediate type) {
        return Optional.empty();
    }

    @Override
    public UnitTestIntermediate addPrompt(String prompt) {
        return new UnitTestLLMIntermediate(prompt);
    }
}
