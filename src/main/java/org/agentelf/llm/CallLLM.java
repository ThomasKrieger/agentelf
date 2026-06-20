package org.agentelf.llm;

public interface CallLLM {

    int size();
    String call(String prompt);

}
