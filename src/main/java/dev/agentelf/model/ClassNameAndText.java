package dev.agentelf.model;

public class ClassNameAndText {

    private final String name;
    private final String text;

    public ClassNameAndText(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}
