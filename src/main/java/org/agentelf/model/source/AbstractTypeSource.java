package org.agentelf.model.source;

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
public abstract class AbstractTypeSource {

    private String packageName;
    private String name;
    private String prompt;
    private String documentation;
    private boolean includePrompt;
    private UnitTest unitTest = new UnitTestLLM();
    private final List<MethodSource> declaredMethods = new ArrayList<>();

    public MethodSource addMethod(TypeDescriptionSource returnType, String name) {
        MethodSource methodSource = new MethodSource();
        methodSource.setName(name);
        methodSource.setReturnType(returnType);
        declaredMethods.add(methodSource);
        return methodSource;
    }

    public void addUnitTestPrompt(String prompt) {
        unitTest.addPrompt(prompt);
    }

    public MethodSource addMethodWithDocumentation(TypeDescriptionSource returnType, String name, String documentation) {
        MethodSource methodSource = addMethod(returnType,name);
        methodSource.setDocumentation(documentation);
        return methodSource;
    }

    public MethodSource addMethodWithDocumentation(String methodDescription, String documentation) {
        MethodSource methodSource = new ParseMethod().parse(methodDescription);
        methodSource.setDocumentation(documentation);
        declaredMethods.add(methodSource);
        return methodSource;
    }

    public void setIncludePrompt(boolean includePrompt) {
        this.includePrompt = includePrompt;
        for(MethodSource method : declaredMethods) {
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

    public List<MethodSource> getAllMethods() {
        return declaredMethods;
    }

}
