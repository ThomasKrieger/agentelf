package dev.agentelf.action;

public interface InternalAction {

    void execute(RunContext runContext);
    void executeAlternative(RunContext runContext, int numberOfAlternative);
    int alternativeCount();

}
