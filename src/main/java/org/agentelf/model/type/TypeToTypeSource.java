package org.agentelf.model.type;

import org.agentelf.model.tosource.*;

import java.util.LinkedList;
import java.util.List;

public class TypeToTypeSource {

    public List<AbstractTypeToSource> transform(TypeModel model) {
        List<AbstractTypeToSource> intermediateTypeList = new LinkedList<>();
        if(model.classes() != null) {
            for(Type type : model.classes()) {
                ClassToSource classIntermediate = new ClassToSource();
                intermediateTypeList.add(classIntermediate);
                new ApplyValuesToTypeWithFieldsSource().applyValues(type,classIntermediate);
                classIntermediate.setPackageName(model.packageName());
            }
        }
        if(model.records() != null) {
            for(Type type : model.records()) {
                RecordToSource recordSource = new RecordToSource();
                intermediateTypeList.add(recordSource);
                new ApplyValuesToTypeWithFieldsSource().applyValues(type,recordSource);
                recordSource.setPackageName(model.packageName());
            }
        }
        if(model.interfaces() != null) {
            for(Interface type : model.interfaces()) {
                InterfaceToSource interfaceSource = new InterfaceToSource();
                intermediateTypeList.add(interfaceSource);
                new ApplyValuesToTypeSource().applyValues(type,interfaceSource);
                interfaceSource.setPackageName(model.packageName());
                if(type.extendsInterfaces() != null) {
                    for(String implement : type.extendsInterfaces()) {
                        interfaceSource.addExtends(TypeDescriptionToSource.create(implement));
                    }
                }
            }
        }
        return intermediateTypeList;
    }

}
