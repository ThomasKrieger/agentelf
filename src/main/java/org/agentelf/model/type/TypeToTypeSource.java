package org.agentelf.model.type;

import org.agentelf.model.source.*;

import java.util.LinkedList;
import java.util.List;

public class TypeToTypeSource {

    public List<AbstractTypeSource> transform(TypeModel model) {
        List<AbstractTypeSource> intermediateTypeList = new LinkedList<>();
        if(model.classes() != null) {
            for(Type type : model.classes()) {
                ClassSource classIntermediate = new ClassSource();
                intermediateTypeList.add(classIntermediate);
                new ApplyValuesToTypeWithFieldsSource().applyValues(type,classIntermediate);
                classIntermediate.setPackageName(model.packageName());
            }
        }
        if(model.records() != null) {
            for(Type type : model.records()) {
                RecordSource recordSource = new RecordSource();
                intermediateTypeList.add(recordSource);
                new ApplyValuesToTypeWithFieldsSource().applyValues(type,recordSource);
                recordSource.setPackageName(model.packageName());
            }
        }
        if(model.interfaces() != null) {
            for(Interface type : model.interfaces()) {
                InterfaceSource interfaceSource = new InterfaceSource();
                intermediateTypeList.add(interfaceSource);
                new ApplyValuesToTypeSource().applyValues(type,interfaceSource);
                interfaceSource.setPackageName(model.packageName());
                if(type.extendsInterfaces() != null) {
                    for(String implement : type.extendsInterfaces()) {
                        interfaceSource.addExtends(TypeDescriptionSource.create(implement));
                    }
                }
            }
        }
        return intermediateTypeList;
    }

}
