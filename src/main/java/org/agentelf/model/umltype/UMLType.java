package org.agentelf.model.umltype;

import lombok.Builder;

import java.lang.String;
import java.util.List;

@Builder
public record UMLType(String packageName,
               String name,
               List<UMLField> fields,
               List<UMLConstructor> constructors,
               List<UMLMethod> methods,
               List<String> extendTypes) {
}
