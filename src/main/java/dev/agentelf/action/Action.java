package dev.agentelf.action;

public interface Action {

    void execute(RunContext runContext);
    void executeAlternative(RunContext runContext, int numberOfAlternative);
    int alternativeCount();

}
