package org.agentelf.model.intermediate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.agentelf.javaparser.ParseField;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractTypeWithDataIntermediate extends AbstractTypeIntermediate {

    private final List<FieldIntermediate> fields = new ArrayList<>();
    private final List<ReferenceTypeDescriptionIntermediate> implementList = new ArrayList<>();


    public FieldIntermediate addField(String description) {
        FieldIntermediate fieldIntermediate = new ParseField().parseField(description);
        fields.add(fieldIntermediate);
        return fieldIntermediate;
    }


    public FieldIntermediate addField(TypeDescriptionIntermediate type, String name) {
        FieldIntermediate fieldIntermediate = new FieldIntermediate();
        fieldIntermediate.setType(type);
        fieldIntermediate.setName(name);
        fields.add(fieldIntermediate);
        return fieldIntermediate;
    }

    public void addImplements(ReferenceTypeDescriptionIntermediate referenceTypeDescriptionIntermediate) {
        implementList.add(referenceTypeDescriptionIntermediate);
    }

    public String getImplementForTemplate() {
        if(implementList.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("implements ");
        for(ReferenceTypeDescriptionIntermediate  referenceTypeDescriptionIntermediate : implementList ) {
            stringBuilder.append(referenceTypeDescriptionIntermediate.getLabelForTemplate());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

}
