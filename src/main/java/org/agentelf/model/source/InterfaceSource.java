package org.agentelf.model.source;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceSource extends AbstractTypeSource {

    private final List<TypeDescriptionSource> extendList = new ArrayList<>();

    public void addExtends(TypeDescriptionSource referenceTypeDescriptionSource) {
        extendList.add(referenceTypeDescriptionSource);
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
        for(TypeDescriptionSource referenceTypeDescriptionSource : extendList ) {
            stringBuilder.append(referenceTypeDescriptionSource.getLabelForTemplate());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

}
