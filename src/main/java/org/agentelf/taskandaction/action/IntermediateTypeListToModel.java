
package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.handle.ReferenceTypeHandle;
import org.agentelf.model.intermediate.AbstractTypeIntermediate;
import org.agentelf.model.intermediate.ModelIntermediate;
import org.agentelf.mustache.ApplyTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IntermediateTypeListToModel {

    /**
     * Iterates over the abstractTypeList
     * calls ApplyTemplate.applyToIntermediateType for each element
     * and stores it im a Map<ReferenceTypeHandle,String> typeToText using getHandle as key
     * and stores the model in Map<ReferenceTypeHandle,AbstractTypeIntermediate> typeToModel
     * creates ModelIntermediate with those two maps
     *
     * @return the created ModelIntermediate
     */
    @Action(arguments = {"intermediateTypeList"}, returnVariable = "modelIntermediate")
    public ModelIntermediate intermediateTypeListToModel(List<AbstractTypeIntermediate> abstractTypeList) throws IOException {
        Map<ReferenceTypeHandle, String> typeToText = new HashMap<>();
        Map<ReferenceTypeHandle, AbstractTypeIntermediate> typeToModel = new HashMap<>();

        for (AbstractTypeIntermediate typeIntermediate : abstractTypeList) {
            ReferenceTypeHandle handle = typeIntermediate.getHandle();
            String text = new ApplyTemplate().applyToIntermediateType(typeIntermediate);
            typeToText.put(handle, text);
            typeToModel.put(handle, typeIntermediate);
        }
        return new ModelIntermediate(typeToText, typeToModel);
    }

}
