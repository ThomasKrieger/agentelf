package org.agentelf.model.source;

import java.util.LinkedList;
import java.util.List;

public class SourceTypeListBuilder {

    private final List<AbstractTypeSource> typeList = new LinkedList<>();

    public ClassSource addClass(String packageName, String name) {
        ClassSource classIntermediate = new ClassSource();
        classIntermediate.setPackageName(packageName);
        classIntermediate.setName(name);
        typeList.add(classIntermediate);
        return classIntermediate;
    }

    public ClassSource addClassAndPrompt(String packageName, String name, String prompt) {
        ClassSource classIntermediate = addClass(packageName,name);
        classIntermediate.setPrompt(prompt);
        return classIntermediate;
    }

    public ClassSource addClassDocumentationAndPrompt(String packageName, String name, String documentation, String prompt) {
        ClassSource classIntermediate = addClass(packageName,name);
        classIntermediate.setDocumentation(documentation);
        classIntermediate.setPrompt(prompt);
        return classIntermediate;
    }

    public ClassSource addClassAndDocumentation(String packageName, String name, String documentation) {
        ClassSource classIntermediate = addClass(packageName,name);
        classIntermediate.setDocumentation(documentation);
        return classIntermediate;
    }

    public List<AbstractTypeSource> build() {
        return typeList;
    }

}
