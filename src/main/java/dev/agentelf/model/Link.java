package dev.agentelf.model;

public class Link {

    private final String name;
    private final String description;
    private final Entity target;

    public Link(String name, String description, Entity target) {
        this.name = name;
        this.description = description;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Entity getTarget() {
        return target;
    }

    public String getTargetName() {
        return target.getName();
    }


}
