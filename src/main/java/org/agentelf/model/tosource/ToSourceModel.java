package org.agentelf.model.tosource;

import lombok.AllArgsConstructor;
import org.agentelf.handle.ReferenceTypeHandle;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ToSourceModel {

    private final Map<ReferenceTypeHandle, TypeToSource> typeToModel;

    public TypeToSource getType(ReferenceTypeHandle key) {
        return typeToModel.get(key);
    }

    public Set<ReferenceTypeHandle> getAllTypeHandles() {
        return typeToModel.keySet();
    }

    /**
     * only for the first iteration
     */
    public String asPrompt() {
        return typeToModel.values().stream()
                .map(TypeToSource::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
