package org.agentelf.model.type;

import org.agentelf.model.tosource.AbstractTypeWithFieldsToSource;
import org.agentelf.model.tosource.TypeDescriptionToSource;

public class ApplyValuesToTypeWithFieldsSource {

    public void applyValues(Type type, AbstractTypeWithFieldsToSource typeSource) {
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
                typeSource.addImplements(TypeDescriptionToSource.create(implement));
            }
        }
    }

}
