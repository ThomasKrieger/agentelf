package org.agentelf.model.tosource;

import java.util.LinkedList;
import java.util.List;

public class SourceTypeListBuilder {

    private final List<AbstractTypeToSource> typeList = new LinkedList<>();

    public ClassToSource addClass(String packageName, String name) {
        ClassToSource classIntermediate = new ClassToSource();
        classIntermediate.setPackageName(packageName);
        classIntermediate.setName(name);
        typeList.add(classIntermediate);
        return classIntermediate;
    }

    public ClassToSource addClassAndPrompt(String packageName, String name, String prompt) {
        ClassToSource classIntermediate = addClass(packageName,name);
        classIntermediate.setPrompt(prompt);
        return classIntermediate;
    }

    public ClassToSource addClassDocumentationAndPrompt(String packageName, String name, String documentation, String prompt) {
        ClassToSource classIntermediate = addClass(packageName,name);
        classIntermediate.setDocumentation(documentation);
        classIntermediate.setPrompt(prompt);
        return classIntermediate;
    }

    public ClassToSource addClassAndDocumentation(String packageName, String name, String documentation) {
        ClassToSource classIntermediate = addClass(packageName,name);
        classIntermediate.setDocumentation(documentation);
        return classIntermediate;
    }

    public List<AbstractTypeToSource> build() {
        return typeList;
    }

}
