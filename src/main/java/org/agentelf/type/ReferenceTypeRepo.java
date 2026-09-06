package org.agentelf.type;

import com.github.javaparser.ast.CompilationUnit;
import lombok.AllArgsConstructor;
import org.agentelf.handle.ReferenceTypeHandle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.empty;


@AllArgsConstructor
public class ReferenceTypeRepo {

    private final Map<String, List<ReferenceTypeHandle>> nameToHandle;
    private final Map<ReferenceTypeHandle, CompilationUnit> handleToSource;

    public Optional<String> lookup(String name) {
        List<ReferenceTypeHandle> result = nameToHandle.get(name);
        if(result == null) {
            return empty();
        }

        if(result.isEmpty()) {
            return empty();
        }
        if(result.size() > 1) {
            throw new RuntimeException("not unique:" + name);
        }
        return Optional.of(handleToSource.get(result.getFirst()).toString());
    }

}
