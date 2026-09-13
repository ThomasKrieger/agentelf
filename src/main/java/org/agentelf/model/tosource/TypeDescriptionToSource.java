package org.agentelf.model.tosource;

import com.github.javaparser.ast.type.Type;

/**
 * either a primitive type or a reference type
 * So we can have the following cases:
 *   primitive Type
 *   java.lang.. no import needed
 *   class
 *   class with generics
 *
 *
 *
 */

public class TypeDescriptionToSource {

    private final String completeText;

    private TypeDescriptionToSource(String completeText) {
        this.completeText = completeText;
    }

    public static TypeDescriptionToSource create(Type type) {
        return new TypeDescriptionToSource(type.asString());
    }

    public static TypeDescriptionToSource create(String text) {
        return new TypeDescriptionToSource(text);
    }

    public String getLabelForTemplate() {
        return completeText;
    }
}
