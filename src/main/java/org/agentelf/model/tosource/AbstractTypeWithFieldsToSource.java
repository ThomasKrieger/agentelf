package org.agentelf.model.tosource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.agentelf.javaparser.ParseField;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractTypeWithFieldsToSource extends AbstractTypeToSource {

    private final List<FieldToSource> fields = new ArrayList<>();
    private final List<TypeDescriptionToSource> implementList = new ArrayList<>();

    public FieldToSource addField(String description) {
        FieldToSource fieldToSource = new ParseField().parseField(description);
        fields.add(fieldToSource);
        return fieldToSource;
    }

    public FieldToSource addField(TypeDescriptionToSource type, String name) {
        FieldToSource fieldToSource = new FieldToSource();
        fieldToSource.setType(type);
        fieldToSource.setName(name);
        fields.add(fieldToSource);
        return fieldToSource;
    }

    public void addImplements(TypeDescriptionToSource referenceTypeDescriptionToSource) {
        implementList.add(referenceTypeDescriptionToSource);
    }

    public String getImplementForTemplate() {
        if(implementList.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("implements ");
        for(TypeDescriptionToSource referenceTypeDescriptionToSource : implementList ) {
            stringBuilder.append(referenceTypeDescriptionToSource.getLabelForTemplate());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

}
