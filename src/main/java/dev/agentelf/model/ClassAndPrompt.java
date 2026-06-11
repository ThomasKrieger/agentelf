package dev.agentelf.model;

public class ClassAndPrompt {

    private final String packageName;
    private final String name;
    private final String prompt;

    public ClassAndPrompt(String packageName,
                          String name,
                          String prompt) {
        this.packageName = packageName;
        this.name = name;
        this.prompt = prompt;
    }

    public String getName() {
        return name;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getPackageName() {
        return packageName;
    }
}
