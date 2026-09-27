package org.agentelf.sourcebuilder;

import com.palantir.javapoet.TypeSpec;
import org.agentelf.handle.ReferenceTypeHandle;

public record ReferenceTypeHandleAndSource(ReferenceTypeHandle handle, TypeSpec.Builder source) {
}
