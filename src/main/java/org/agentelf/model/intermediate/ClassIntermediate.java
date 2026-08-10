package org.agentelf.model.intermediate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClassIntermediate extends AbstractTypeWithDataIntermediate{

    /**
     * is null if this class does not extend another class
     */
    private ReferenceTypeDescriptionIntermediate extendsType;

    public String getType() {
        return "class";
    }

}
