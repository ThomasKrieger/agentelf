package org.agentelf.model.tosource;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClassToSource extends AbstractTypeWithFieldsToSource {

    /**
     * is null if this class does not extend another class
     */
    private TypeDescriptionToSource extendsType;

    public String getType() {
        return "class";
    }

}
