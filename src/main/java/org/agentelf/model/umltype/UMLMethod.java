package org.agentelf.model.umltype;

import lombok.Builder;

import java.lang.String;
import java.util.List;

@Builder
public record UMLMethod(String name,
                 List<UMLArgument> arguments,
                 String returnType) {
}
