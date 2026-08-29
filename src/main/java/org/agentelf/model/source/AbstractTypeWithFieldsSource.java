package org.agentelf.model.source;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.agentelf.javaparser.ParseField;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractTypeWithFieldsSource extends AbstractTypeSource {

    private final List<FieldSource> fields = new ArrayList<>();
    private final List<TypeDescriptionSource> implementList = new ArrayList<>();

    public FieldSource addField(String description) {
        FieldSource fieldSource = new ParseField().parseField(description);
        fields.add(fieldSource);
        return fieldSource;
    }

    public FieldSource addField(TypeDescriptionSource type, String name) {
        FieldSource fieldSource = new FieldSource();
        fieldSource.setType(type);
        fieldSource.setName(name);
        fields.add(fieldSource);
        return fieldSource;
    }

    public void addImplements(TypeDescriptionSource referenceTypeDescriptionSource) {
        implementList.add(referenceTypeDescriptionSource);
    }

    public String getImplementForTemplate() {
        if(implementList.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("implements ");
        for(TypeDescriptionSource referenceTypeDescriptionSource : implementList ) {
            stringBuilder.append(referenceTypeDescriptionSource.getLabelForTemplate());
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

}
