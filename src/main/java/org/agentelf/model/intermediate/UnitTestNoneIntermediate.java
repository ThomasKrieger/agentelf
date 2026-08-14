package org.agentelf.model.intermediate;

import java.util.Optional;

public class UnitTestNoneIntermediate implements UnitTestIntermediate{
    @Override
    public Optional<String> getPrompt(AbstractTypeIntermediate type) {
        return Optional.empty();
    }
}
