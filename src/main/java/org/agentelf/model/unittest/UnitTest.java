package org.agentelf.model.unittest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

}
