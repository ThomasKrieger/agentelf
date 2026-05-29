package dev.agentelf.model;

import java.util.LinkedList;
import java.util.List;

public class Entity {

    private final String name;
    private final String description;
    private final List<Link> outgoing = new LinkedList<>();

    public Entity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Link> getOutgoing() {
        return outgoing;
    }
}
