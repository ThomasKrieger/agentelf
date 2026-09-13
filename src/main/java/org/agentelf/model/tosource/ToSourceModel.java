package org.agentelf.model.tosource;

import org.agentelf.handle.ReferenceTypeHandle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ToSourceModel {

    private final Map<ReferenceTypeHandle,String> typeToText ;
    private final Map<ReferenceTypeHandle, AbstractTypeToSource> typeToModel;

    public ToSourceModel(Map<ReferenceTypeHandle, String> typeToText,
                         Map<ReferenceTypeHandle, AbstractTypeToSource> typeToModel) {
        this.typeToText = typeToText;
        this.typeToModel = typeToModel;
    }

    public String createTextForTypesExcept(ReferenceTypeHandle except) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter result = new PrintWriter(stringWriter);
        for(Map.Entry<ReferenceTypeHandle,String> entry : typeToText.entrySet()) {
            if(! entry.getKey().equals(except)) {
                result.println(entry.getValue());
            }
        }
        return stringWriter.toString();
    }

    public String createTextForAllTypes() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter result = new PrintWriter(stringWriter);
        for(Map.Entry<ReferenceTypeHandle,String> entry : typeToText.entrySet()) {
            result.println(entry.getValue());
        }
        return stringWriter.toString();
    }

    public void setGeneratedType(ReferenceTypeHandle typeHandle, String text) {
        typeToText.put(typeHandle,text);
    }

    public AbstractTypeToSource getType(ReferenceTypeHandle typeHandle) {
        return typeToModel.get(typeHandle);
    }

    public Set<ReferenceTypeHandle> getAllTypeHandles() {
        return typeToModel.keySet();
    }

    public Collection<AbstractTypeToSource> getAllTypes() {
        return typeToModel.values();
    }


}
