package dev.agentelf.yaml;

import java.util.Deque;

public interface YamlParserState {

    TaskOrAction taskOrAction();
    void process(String value, Deque<YamlParserState> stack, TaskOrActionBuilder builder);

}
