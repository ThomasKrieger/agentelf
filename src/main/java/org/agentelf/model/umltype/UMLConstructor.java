package org.agentelf.model.umltype;

import lombok.Builder;

import java.util.List;

@Builder
public record UMLConstructor(List<UMLArgument> arguments) {
}
