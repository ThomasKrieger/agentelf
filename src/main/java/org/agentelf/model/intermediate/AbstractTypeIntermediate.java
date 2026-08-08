package org.agentelf.model.intermediate;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractTypeIntermediate {

    private String packageName;
    private String name;
    private String prompt;
    private String documentation;
    private UnitTestIntermediate unitTest;
    private final List<MethodIntermediate> methods = new ArrayList<>();

}
