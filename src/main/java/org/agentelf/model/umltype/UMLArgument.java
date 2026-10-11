package org.agentelf.model.umltype;

import lombok.Builder;

@Builder
public record UMLArgument(String type, String name) {
}
