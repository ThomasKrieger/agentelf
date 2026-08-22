package org.agentelf.model.intermediate;

import lombok.Data;
import org.agentelf.javaparser.ParseMethod;
import org.agentelf.model.handle.ReferenceTypeHandle;
import org.agentelf.model.unittest.UnitTest;
import org.agentelf.model.unittest.UnitTestLLM;
import org.agentelf.mustache.ApplyTemplate;

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
    private UnitTest unitTest = new UnitTestLLM();
    private final List<MethodIntermediate> declaredMethods = new ArrayList<>();

    public MethodIntermediate addMethod(TypeDescriptionIntermediate returnType, String name) {
        MethodIntermediate methodIntermediate = new MethodIntermediate();
        methodIntermediate.setName(name);
        methodIntermediate.setReturnType(returnType);
        declaredMethods.add(methodIntermediate);
        return methodIntermediate;
    }

    public void addUnitTestPrompt(String prompt) {
        unitTest.addPrompt(prompt);
    }

    public MethodIntermediate addMethodWithDocumentation(TypeDescriptionIntermediate returnType, String name, String documentation) {
        MethodIntermediate methodIntermediate = addMethod(returnType,name);
        methodIntermediate.setDocumentation(documentation);
        return methodIntermediate;
    }

    public MethodIntermediate addMethodWithDocumentation(String methodDescription, String documentation) {
        MethodIntermediate methodIntermediate = new ParseMethod().parse(methodDescription);
        methodIntermediate.setDocumentation(documentation);
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

    public ReferenceTypeHandle getHandle() {
        return new ReferenceTypeHandle(packageName,name);
    }

    public Optional<String> getUnitTestPrompt(ApplyTemplate applyTemplate) throws IOException {
        return unitTest.getPrompt(applyTemplate,this);
    }

    public List<MethodIntermediate> getAllMethods() {
        return declaredMethods;
    }

}
