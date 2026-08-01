package org.agentelf.model.generatetype.llm;

import org.agentelf.model.common.FieldDeclaration;
import org.agentelf.model.common.MethodDeclaration;

import java.util.List;

public record GenerateType(String name,
                           String packageName,
                           String id,
                           String typeDeclaration,
                           String documentation,
                           String prompt,
                           List<FieldDeclaration> fields,
                           List<MethodDeclaration> methods) {
}
