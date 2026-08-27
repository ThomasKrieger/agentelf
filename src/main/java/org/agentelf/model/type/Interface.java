package org.agentelf.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.agentelf.model.unittest.UnitTest;

import java.util.List;

public record Interface(String name,
                        String documentation,
                        List<String> annotations,
                        List<Method> methods,
                        @JsonProperty("extends") List<String> extendsInterfaces,
                        UnitTest unitTest) {
}
