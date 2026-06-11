package dev.agentelf.model.state;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class State {

    private final String name;
    private final List<Link> incoming = new LinkedList<>();

    public State(String name) {
        this.name = name;
    }
}
