package org.agentelf.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.agentelf.model.unittest.UnitTest;

import java.util.List;

public record Type(String name,
                   List<String> fields,
                   List<Function> methods,
                   @JsonProperty("implements") List<String> implementsInterfaces,
                   UnitTest unitTest) {
}
