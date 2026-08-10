package org.agentelf.model.intermediate;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractTypeIntermediate {

    private String packageName;
    private String name;
    private String prompt;
    private String documentation;
    private boolean includePrompt;
    private UnitTestIntermediate unitTest;
    private final List<MethodIntermediate> declaredMethods = new ArrayList<>();

    public MethodIntermediate addMethod(String name, TypeDescriptionIntermediate returnType) {
        MethodIntermediate methodIntermediate = new MethodIntermediate();
        methodIntermediate.setName(name);
        methodIntermediate.setReturnType(returnType);
        declaredMethods.add(methodIntermediate);
        return methodIntermediate;
    }

    public void setIncludePrompt(boolean includePrompt) {
        this.includePrompt = includePrompt;
        for(MethodIntermediate method : declaredMethods) {
            method.setIncludePrompt(includePrompt);
        }
    }

    public String getPromptForTemplate() {
        if(includePrompt) {
            return prompt;
        }
        return "";
    }

}
