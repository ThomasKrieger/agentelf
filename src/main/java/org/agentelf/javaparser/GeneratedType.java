
package org.agentelf.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import org.agentelf.handle.MethodHandle;

/**
 * GeneratedType handles the modification of annotations and documentation 
 * for classes and methods within a Java compilation unit.
 */
public class GeneratedType {

    private CompilationUnit compilationUnit;

    /**
     * Iterates over all Methods of the compilationUnit. Create a new MethodHandle for each method
     * and compares it with the given methodHandle. If it is equal parses the given annotationSource. 
     * And checks if an annotation with the given name already exists. If yes replaces the annotation, 
     * if not adds the annotation. Throws an exception if no method for the given methodHandle was found.
     */
    public void setMethodAnnotation(MethodHandle methodHandle, String annotationSource) {
        AnnotationExpr newAnnotation = StaticJavaParser.parseAnnotation(annotationSource);
        String annotationName = newAnnotation.getNameAsString();
        boolean methodFound = false;

        for (MethodDeclaration method : compilationUnit.findAll(MethodDeclaration.class)) {
            MethodHandle currentHandle = new MethodHandle(method.getNameAsString());
            if (currentHandle.equals(methodHandle)) {
                method.getAnnotationByName(annotationName).ifPresent(AnnotationExpr::remove);
                method.addAnnotation(newAnnotation);
                methodFound = true;
                break;
            }
        }

        if (!methodFound) {
            throw new RuntimeException("No method found for the given method handle: " + methodHandle);
        }
    }

    /**
     * Iterates over all Methods of the compilationUnit. Create a new MethodHandle for each method
     * and compares it with the given methodHandle. If it is equal sets the given documentation 
     * as documentation of this method.
     */
    public void setMethodDocumentation(MethodHandle methodHandle, String documentation) {
        boolean methodFound = false;

        for (MethodDeclaration method : compilationUnit.findAll(MethodDeclaration.class)) {
            MethodHandle currentHandle = new MethodHandle(method.getNameAsString());
            if (currentHandle.equals(methodHandle)) {
                method.setJavadocComment(documentation);
                methodFound = true;
                break;
            }
        }

        if (!methodFound) {
            throw new RuntimeException("No method found for the given method handle: " + methodHandle);
        }
    }

    /**
     * Checks if an annotation with the same name exists on the class. 
     * If yes replaces the annotation, if not adds the annotation.
     */
    public void setClassAnnotation(String annotationSource) {
        TypeDeclaration<?> type = compilationUnit.getTypes().getFirst()
                .orElseThrow(() -> new RuntimeException("No primary type found in compilation unit"));

        AnnotationExpr newAnnotation = StaticJavaParser.parseAnnotation(annotationSource);
        type.getAnnotationByName(newAnnotation.getNameAsString()).ifPresent(AnnotationExpr::remove);
        type.addAnnotation(newAnnotation);
    }

    /**
     * Sets the given documentation as class documentation.
     */
    public void setClassDocumentation(String documentation) {
        TypeDeclaration<?> type = compilationUnit.getTypes().getFirst()
                .orElseThrow(() -> new RuntimeException("No primary type found in compilation unit"));
        
        type.setJavadocComment(documentation);
    }
}
