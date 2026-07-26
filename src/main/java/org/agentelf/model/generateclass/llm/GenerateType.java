package org.agentelf.model.generateclass.llm;

import org.agentelf.model.common.Declaration;

import java.util.List;

public record GenerateType(String name, String packageName, String id,  String typeDeclaration, String documentation, String prompt, List<Declaration> fieldsOrMethods) {
}
