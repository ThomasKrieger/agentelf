package org.agentelf.model.function;

import lombok.Builder;
import org.agentelf.type.Type;

@Builder
public record FunctionArgument(Type type, String name) {
}
