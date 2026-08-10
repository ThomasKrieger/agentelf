package org.agentelf.model.intermediate;

import java.util.List;

public class IntermediateTypeListFactory {

    public static List<AbstractTypeIntermediate> create() {
        IntermediateTypeListBuilder builder = new IntermediateTypeListBuilder();

        builder.addClass("org.agentelf.taskandaction.action", "CreateIntermediateModel");
        builder.addClass("org.agentelf.model.intermediate", "ModelIntermediate");

        return builder.build();
    }

}
