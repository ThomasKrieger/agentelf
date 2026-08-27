
package org.agentelf.taskandaction.action;

import lombok.RequiredArgsConstructor;
import org.agentelf.api.Action;
import org.agentelf.model.handle.ReferenceTypeHandle;
import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.SourceModel;
import org.agentelf.mustache.ApplyTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SourceModelTypeListToModel {

    private final ApplyTemplate applyTemplate;

    /**
     * Iterates over the abstractTypeList
     * calls ApplyTemplate.applyToIntermediateType for each element
     * and stores it im a Map<ReferenceTypeHandle,String> typeToText using getHandle as key
     * and stores the model in Map<ReferenceTypeHandle,AbstractTypeIntermediate> typeToModel
     * creates SourceModel with those two maps
     *
     * @return the created SourceModel
     */
    @Action(arguments = {"intermediateTypeList"}, returnVariable = "sourceModel")
    public SourceModel intermediateTypeListToModel(List<AbstractTypeSource> abstractTypeList) throws IOException {
        Map<ReferenceTypeHandle, String> typeToText = new HashMap<>();
        Map<ReferenceTypeHandle, AbstractTypeSource> typeToModel = new HashMap<>();

        for (AbstractTypeSource typeIntermediate : abstractTypeList) {
            ReferenceTypeHandle handle = typeIntermediate.getHandle();
            String text = applyTemplate.applyToIntermediateType(typeIntermediate);
            typeToText.put(handle, text);
            typeToModel.put(handle, typeIntermediate);
        }
        return new SourceModel(typeToText, typeToModel);
    }

}
