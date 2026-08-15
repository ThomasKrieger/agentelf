package org.agentelf.model.intermediate;

import org.agentelf.mustache.ApplyTemplate;

import java.io.IOException;
import java.util.Optional;

public interface UnitTestIntermediate {

    Optional<String> getPrompt(ApplyTemplate applyTemplate, AbstractTypeIntermediate type) throws IOException;
    UnitTestIntermediate addPrompt(String prompt);
}
