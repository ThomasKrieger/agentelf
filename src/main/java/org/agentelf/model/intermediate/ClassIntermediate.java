package org.agentelf.model.intermediate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClassIntermediate extends AbstractTypeWithDataIntermediate{

    public String getType() {
        return "class";
    }

}
