package org.agentelf.type;

import com.github.javaparser.ast.CompilationUnit;
import lombok.AllArgsConstructor;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.inheritancegraph.MethodsToBeImplemented;

@AllArgsConstructor
public class FromSource implements ModelOrSource {

    private final ReferenceTypeHandle handle;
    private CompilationUnit compilationUnit;

    @Override
    public void uodate(CompilationUnit parse) {
        compilationUnit = parse;
    }

    @Override
    public void addToMethodsToBeImplemented(MethodsToBeImplemented methodsToBeImplemented) {

    }


}
