package org.agentelf.model.source;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FieldSource {

    private List<String> annotations = new ArrayList<>();
    private TypeDescriptionSource type;
    private String name;

}
