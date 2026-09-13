package org.agentelf.model.tosource;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecordToSource extends AbstractTypeWithFieldsToSource {

    public String getType() {
        return "record";
    }
}
