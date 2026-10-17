package org.agentelf.type;

import com.github.javaparser.ast.CompilationUnit;
import org.agentelf.inheritancegraph.MethodsToBeImplemented;

public interface ModelOrSource {
    void uodate(CompilationUnit parse);
    void addToMethodsToBeImplemented(MethodsToBeImplemented methodsToBeImplemented);
}
