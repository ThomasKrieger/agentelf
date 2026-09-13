package org.agentelf.model.tosource;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceToSource extends AbstractTypeToSource {

    private final List<TypeDescriptionToSource> extendList = new ArrayList<>();

    public void addExtends(TypeDescriptionToSource referenceTypeDescriptionToSource) {
        extendList.add(referenceTypeDescriptionToSource);
    }

    public String getType() {
        return "interface";
    }

    public String getImplementForTemplate() {
        if(extendList.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("extends ");
        for(TypeDescriptionToSource referenceTypeDescriptionToSource : extendList ) {
            stringBuilder.append(referenceTypeDescriptionToSource.getLabelForTemplate());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

    @Override
    public List<FieldToSource> getFields() {
        return List.of();
    }
}
