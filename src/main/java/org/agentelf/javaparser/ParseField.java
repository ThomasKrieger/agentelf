
package org.agentelf.javaparser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import org.agentelf.model.source.FieldSource;
import org.agentelf.model.source.TypeDescriptionSource;

public class ParseField {


    public FieldSource parseField(String description) {
        TypeAndName typeAndName = parseFieldInternal(description);
        FieldSource fieldSource = new FieldSource();
        fieldSource.setName(typeAndName.name());
        fieldSource.setType(TypeDescriptionSource.create(typeAndName.type()));
        return fieldSource;
    }

    /**
     * Parses a field description using StaticJavaParser and returns a TypeAndName record.
     * 
     * @param description The string representation of the field (e.g., "private String name;")
     * @return A TypeAndName object containing the type and name of the field.
     */
    public TypeAndName parseFieldInternal(String description) {
        String normalized = new NormalizeDescription().normalize(description);
        BodyDeclaration<?> declaration = StaticJavaParser.parseBodyDeclaration(normalized);
        
        if (declaration instanceof FieldDeclaration fieldDeclaration) {
            // A FieldDeclaration can technically contain multiple variables (e.g., int a, b;)
            // We take the first one for the purpose of returning a single TypeAndName.
            VariableDeclarator variable = fieldDeclaration.getVariable(0);
            return new TypeAndName(variable.getType(), variable.getNameAsString());
        } else {
            throw new IllegalArgumentException("The provided description is not a valid field declaration.");
        }
    }
}
