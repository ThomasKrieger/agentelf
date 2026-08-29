package org.agentelf.model.source;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecordSource extends AbstractTypeWithFieldsSource {

    public String getType() {
        return "record";
    }
}
