package org.agentelf.model.tosource;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FieldToSource {

    private List<String> annotations = new ArrayList<>();
    private TypeDescriptionToSource type;
    private String name;

}
