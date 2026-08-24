package org.agentelf.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TypeModel(@JsonProperty("package") String packageName,
                        List<Type> records,
                        List<Type> classes) {
}
