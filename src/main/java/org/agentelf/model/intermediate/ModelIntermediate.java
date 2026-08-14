package org.agentelf.model.intermediate;

import org.agentelf.model.handle.ReferenceTypeHandle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

public class ModelIntermediate {

    private final Map<ReferenceTypeHandle,String> typeToText ;
    private final Map<ReferenceTypeHandle,AbstractTypeIntermediate> typeToModel;

    public ModelIntermediate(Map<ReferenceTypeHandle, String> typeToText,
                             Map<ReferenceTypeHandle, AbstractTypeIntermediate> typeToModel) {
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

    public AbstractTypeIntermediate getType(ReferenceTypeHandle typeHandle) {
        return typeToModel.get(typeHandle);
    }

    public Set<ReferenceTypeHandle> getAllTypeHandles() {
        return typeToModel.keySet();
    }


}
