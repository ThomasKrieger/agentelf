package org.agentelf.model.generatewithllm;

import org.agentelf.model.common.Declaration;

import java.util.List;

public record GenerateType(String name, String packageName, String typeDeclaration, String prompt, List<Declaration> fieldsOrMethods) {
}
