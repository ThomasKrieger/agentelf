package org.agentelf.model.intermediate;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractTypeWithDataIntermediate extends AbstractTypeIntermediate {

    private final List<FieldIntermediate> fields = new ArrayList<>();
    private final List<TypeDescriptionIntermediate> implementsList = new ArrayList<>();


}
