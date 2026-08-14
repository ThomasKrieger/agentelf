package org.agentelf.model.intermediate;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A fully qualified Name used in extends and implements class.
 * For Generic Classes it contains the resolved types.
 */
@Setter
@AllArgsConstructor
public class ReferenceTypeDescriptionIntermediate implements TypeDescriptionIntermediate{

    private String packageName;
    private String name;
    private final List<String> genericPlaceholderNames = new ArrayList<>();
    private final Map<String,ReferenceTypeDescriptionIntermediate> genericPlaceholderNameToResolvedType = new HashMap<>();


    @Override
    public String getLabelForTemplate() {
        return packageName +  "." + name;
    }
}
