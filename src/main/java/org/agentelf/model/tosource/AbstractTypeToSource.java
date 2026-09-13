package org.agentelf.model.tosource;

import lombok.Data;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.javaparser.ParseMethod;
import org.agentelf.model.unittest.UnitTest;
import org.agentelf.model.unittest.UnitTestLLM;
import org.agentelf.mustache.ApplyTemplate;

import java.io.IOException;
import java.util.*;

@Data
public abstract class AbstractTypeToSource {

    private String packageName;
    private List<String> annotations = new ArrayList<>();
    private String name;
    private String prompt;
    private String documentation;
    private boolean includePrompt;
    private UnitTest unitTest = new UnitTestLLM();
    private final List<MethodToSource> declaredMethods = new ArrayList<>();

    public MethodToSource addMethod(TypeDescriptionToSource returnType, String name) {
        MethodToSource methodToSource = new MethodToSource();
        methodToSource.setName(name);
        methodToSource.setReturnType(returnType);
        declaredMethods.add(methodToSource);
        return methodToSource;
    }

    public void addUnitTestPrompt(String prompt) {
        unitTest.addPrompt(prompt);
    }

    public MethodToSource addMethodWithDocumentation(TypeDescriptionToSource returnType, String name, String documentation) {
        MethodToSource methodToSource = addMethod(returnType,name);
        methodToSource.setDocumentation(documentation);
        return methodToSource;
    }

    public MethodToSource addMethodWithDocumentation(String methodDescription, String documentation) {
        MethodToSource methodToSource = new ParseMethod().parse(methodDescription);
        methodToSource.setDocumentation(documentation);
        declaredMethods.add(methodToSource);
        return methodToSource;
    }

    public void setIncludePrompt(boolean includePrompt) {
        this.includePrompt = includePrompt;
        for(MethodToSource method : declaredMethods) {
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

    public List<MethodToSource> getAllMethods() {
        return declaredMethods;
    }

    public Set<String> getAllUsedTypes() {
        Set<String> result = new HashSet<>();
        for(MethodToSource methodToSource : getAllMethods()) {
            result.add(methodToSource.getReturnType().getLabelForTemplate());
            for(VariableDeclarationToSource parameter : methodToSource.getParameterList()) {
                result.add(parameter.getType().getLabelForTemplate());
            }
        }
        for(FieldToSource fieldToSource : getFields()) {
            result.add(fieldToSource.getType().getLabelForTemplate());
        }
        return result;
    }



    protected abstract List<FieldToSource> getFields();

}
