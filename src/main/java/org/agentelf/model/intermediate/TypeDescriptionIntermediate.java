package org.agentelf.model.intermediate;

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

public class TypeDescriptionIntermediate {

    private final String completeText;

    private TypeDescriptionIntermediate(String completeText) {
        this.completeText = completeText;
    }

    public static TypeDescriptionIntermediate create(Type type) {
        return new TypeDescriptionIntermediate(type.asString());
    }

    public static TypeDescriptionIntermediate create(String text) {
        return new TypeDescriptionIntermediate(text);
    }

    public String getLabelForTemplate() {
        return completeText;
    }
}
