package org.agentelf.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompilationUnitWrapper {

    private final CompilationUnit compilationUnit;

    public boolean isAbstract() {
        for(TypeDeclaration type : compilationUnit.getTypes()) {
            if(type.isClassOrInterfaceDeclaration()) {
                return type.asClassOrInterfaceDeclaration().isInterface();
            }
        }
        return false;
    }

}
