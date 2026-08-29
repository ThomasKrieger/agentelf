package org.agentelf.model.type;

import org.agentelf.model.source.AbstractTypeWithFieldsSource;
import org.agentelf.model.source.TypeDescriptionSource;

public class ApplyValuesToTypeWithFieldsSource {

    public void applyValues(Type type, AbstractTypeWithFieldsSource typeSource) {
        new ApplyValuesToTypeSource().applyValues(type,typeSource);
        if(type.fields() != null) {
            for(Field field :  type.fields()) {
                var source = typeSource.addField(field.declaration());
                if(field.annotations() != null) {
                    source.setAnnotations(field.annotations());
                }
            }
        }
        if(type.implementsInterfaces() != null) {
            for(String implement : type.implementsInterfaces()) {
                typeSource.addImplements(TypeDescriptionSource.create(implement));
            }
        }
    }

}
