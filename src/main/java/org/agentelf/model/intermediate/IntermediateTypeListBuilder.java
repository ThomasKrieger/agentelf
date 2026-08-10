package org.agentelf.model.intermediate;

import java.util.LinkedList;
import java.util.List;

public class IntermediateTypeListBuilder {

    private final List<AbstractTypeIntermediate> typeList = new LinkedList<>();

    public ClassIntermediate addClass(String packageName, String name) {
        ClassIntermediate classIntermediate = new ClassIntermediate();
        classIntermediate.setPackageName(packageName);
        classIntermediate.setName(name);
        typeList.add(classIntermediate);
        return classIntermediate;
    }

    public List<AbstractTypeIntermediate> build() {
        return typeList;
    }

}
