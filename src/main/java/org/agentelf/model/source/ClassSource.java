package org.agentelf.model.source;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClassSource extends AbstractTypeWithDataSource {

    /**
     * is null if this class does not extend another class
     */
    private TypeDescriptionSource extendsType;

    public String getType() {
        return "class";
    }

}
