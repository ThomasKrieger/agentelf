package org.agentelf.model.action;

import org.agentelf.model.common.FieldDeclaration;

import java.util.List;
import java.util.Optional;

public record Action(String name,
                     String documentation,
                     String prompt,
                     List<ActionVariable> parameters,
                     Optional<ActionVariable> returnValue,
                     List<FieldDeclaration> fields) {
}
