package org.agentelf.model.unittest;

import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.mustache.ApplyTemplate;

import java.util.Optional;

public class UnitTestNone implements UnitTest {
    @Override
    public Optional<String> getPrompt(ApplyTemplate applyTemplate, AbstractTypeSource type) {
        return Optional.empty();
    }

    @Override
    public UnitTest addPrompt(String prompt) {
        return new UnitTestLLM(prompt);
    }
}
