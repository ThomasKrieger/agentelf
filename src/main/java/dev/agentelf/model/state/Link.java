package dev.agentelf.model.state;

import lombok.Getter;

@Getter
public class Link {

    private final String name;
    private final State source;
    private final String description;

    public Link(String name, State source, String description) {
        this.name = name;
        this.source = source;
        this.description = description;
    }
}
