package org.agentelf.model.intermediate;

import lombok.Data;
import org.agentelf.model.handle.ReferenceTypeHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public abstract class AbstractTypeIntermediate {

    private String packageName;
    private String name;
    private String prompt;
    private String documentation;
    private boolean includePrompt;
    private UnitTestIntermediate unitTest = new UnitTestLLMIntermediate();
    private final List<MethodIntermediate> declaredMethods = new ArrayList<>();

    public MethodIntermediate addMethod(TypeDescriptionIntermediate returnType, String name) {
        MethodIntermediate methodIntermediate = new MethodIntermediate();
        methodIntermediate.setName(name);
        methodIntermediate.setReturnType(returnType);
        declaredMethods.add(methodIntermediate);
        return methodIntermediate;
    }

    public MethodIntermediate addMethodWithDocumentation(TypeDescriptionIntermediate returnType, String name, String documentation) {
        MethodIntermediate methodIntermediate = addMethod(returnType,name);
        methodIntermediate.setDocumentation(documentation);
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

    public ReferenceTypeHandle getHandle() {
        return new ReferenceTypeHandle(packageName,name);
    }

    public Optional<String> getUnitTestPrompt() throws IOException {
        return unitTest.getPrompt(this);
    }

}
