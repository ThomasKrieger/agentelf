package org.agentelf.model.unittest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.agentelf.model.intermediate.AbstractTypeIntermediate;
import org.agentelf.mustache.ApplyTemplate;

import java.io.IOException;
import java.util.Optional;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UnitTestLLM.class, name = "llm"),
        @JsonSubTypes.Type(value = UnitTestNone.class, name = "none")
})
public interface UnitTest {

    Optional<String> getPrompt(ApplyTemplate applyTemplate, AbstractTypeIntermediate type) throws IOException;
    UnitTest addPrompt(String prompt);
}
