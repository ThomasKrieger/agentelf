package org.agentelf.model.intermediate;

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
public class ReferenceTypeDescriptionIntermediate implements TypeDescriptionIntermediate{

    private String packageName;
    private String name;
    private List<String> genericPlaceholderNames = new ArrayList<>();
    private Map<String,ReferenceTypeDescriptionIntermediate> genericPlaceholderNameToResolvedType = new HashMap<>();


    @Override
    public String getLabelForTemplate() {
        return packageName +  "." + name;
    }
}
