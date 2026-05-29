package dev.agentelf.cli;

import dev.agentelf.llm.CallLLM;

public class AgentElf {

    public static void main(String[] arguments) {
        String prompt = """
                YamlParser parses yaml using snakeyaml events.
                For the first element in the yaml a TaskOrAction is created
                using TaskOrActionBuilder 
                For each Element following actions tag a new TaskOrAction is created#
                For all other tags setProperty of TaskOrAction is called
                
                 It uses a stack to store the created TaskOrAction.
                """;
        String classes = """
                public interface TaskOrAction {
    
                    void addTask(TaskOrAction taskOrAction);
                    void setProperty(String propertyName, Object value);
                }

                public interface TaskOrActionBuilder {
                        TaskOrAction create(String name);
                }
                """;
        String command = """
                implement
                public class YamlParser {
                        public void parse(Reader reader) {
                }
                Output exactly one compilable Java class and nothing else.
                """;
        String result = new CallLLM().call(prompt + classes + command);
        System.out.println(result);
    }

}
