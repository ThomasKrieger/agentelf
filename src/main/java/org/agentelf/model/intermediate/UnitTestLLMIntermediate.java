package org.agentelf.model.intermediate;

import org.agentelf.mustache.ApplyTemplate;

import java.io.IOException;
import java.util.Optional;

public class UnitTestLLMIntermediate implements UnitTestIntermediate {
    @Override
    public Optional<String> getPrompt(ApplyTemplate applyTemplate,AbstractTypeIntermediate type) throws IOException {
        return Optional.of( applyTemplate.apply(type,"promptUnitTest.mustache"));
    }
}
