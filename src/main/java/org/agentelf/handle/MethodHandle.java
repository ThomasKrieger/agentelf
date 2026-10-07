package org.agentelf.handle;

import java.util.List;

public record MethodHandle(String name, List<TypeHandle> argumentTypes, TypeHandle returnType) {
}
