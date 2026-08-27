package org.agentelf.model.source;

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

public class TypeDescriptionSource {

    private final String completeText;

    private TypeDescriptionSource(String completeText) {
        this.completeText = completeText;
    }

    public static TypeDescriptionSource create(Type type) {
        return new TypeDescriptionSource(type.asString());
    }

    public static TypeDescriptionSource create(String text) {
        return new TypeDescriptionSource(text);
    }

    public String getLabelForTemplate() {
        return completeText;
    }
}
