package org.agentelf.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.agentelf.handle.MethodHandle;

public class GeneratedType {

    private CompilationUnit compilationUnit;

    /**
     * Iterates over all Methods of the compilationUnit. Create a new MethodHandle for each method
     * and compares it with the given methodHandle. If it is equal adds the given annotationSource as 
     * annotation
     */
    public void setMethodAnnotation(MethodHandle methodHandle, String annotationSource) {
        compilationUnit.findAll(MethodDeclaration.class).forEach(method -> {
            MethodHandle currentHandle = new MethodHandle(method.getNameAsString());
            if (currentHandle.equals(methodHandle)) {
                method.addAnnotation(StaticJavaParser.parseAnnotation(annotationSource));
            }
        });
    }

    /**
     * Iterates over all Methods of the compilationUnit. Create a new MethodHandle for each method
     * and compares it with the given methodHandle. If it is equal sets the given documentation as documentation
     * of this method
     */
    public void setMethodDocumentation(MethodHandle methodHandle, String documentation) {
        compilationUnit.findAll(MethodDeclaration.class).forEach(method -> {
            MethodHandle currentHandle = new MethodHandle(method.getNameAsString());
            if (currentHandle.equals(methodHandle)) {
                method.setJavadocComment(documentation);
            }
        });
    }

    /**
     * Sets the given annotation as class annotation
     */
    public void setClassAnnotation(String annotationSource) {
        compilationUnit.getTypes().getFirst().ifPresent(type -> {
            type.addAnnotation(StaticJavaParser.parseAnnotation(annotationSource));
        });
    }

    /**
     * Sets the given documentation as class documentation
     */
    public void setClassDocumentation(String documentation) {
        compilationUnit.getTypes().getFirst().ifPresent(type -> {
            type.setJavadocComment(documentation);
        });
    }
}