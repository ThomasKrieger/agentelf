package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.tosource.AbstractTypeToSource;
import org.agentelf.model.type.TypeModel;
import org.agentelf.model.type.TypeToTypeSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeModelToIntermediateTypeList {

    @Action(arguments = {"model"},
            returnVariable = "intermediateTypeList")
    public List<AbstractTypeToSource> typeModelTointermediateTypeList(TypeModel model) {
        return new TypeToTypeSource().transform(model);
    }

}
