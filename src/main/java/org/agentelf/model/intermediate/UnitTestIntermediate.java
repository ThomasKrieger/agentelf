package org.agentelf.model.intermediate;

import java.io.IOException;
import java.util.Optional;

public interface UnitTestIntermediate {

    Optional<String> getPrompt(AbstractTypeIntermediate type) throws IOException;

}
