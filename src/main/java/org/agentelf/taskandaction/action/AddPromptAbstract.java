package org.agentelf.taskandaction.action;


public abstract class AddPromptAbstract {

    protected String addPrompt(String prompt, String addToPrompt) {
        return  prompt + System.lineSeparator() + addToPrompt + System.lineSeparator();
    }

}
