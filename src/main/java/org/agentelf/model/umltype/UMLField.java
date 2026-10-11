package org.agentelf.model.umltype;

import lombok.Builder;

import java.lang.String;

@Builder
public record UMLField(String type,
                String name) {
}
