package dev.agentelf.cli;

import dev.agentelf.file.FileOutput;
import dev.agentelf.llm.CallLLM;
import dev.agentelf.meta.BuilderStateFactory;
import dev.agentelf.meta.ParserStateFactory;
import dev.agentelf.model.ClassNameAndText;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AgentElf {

    public static void main(String[] arguments) {
        String classes = """
  public interface BuilderState {

    BuilderState addAction(String name);
    BuilderState addProperty(String name, String value);
    BuilderState addSequence(String name);
    BuilderState addScalarToSequence(String name);

}
                
                """;


      /*  String prompt = """
                YamlParser parses yaml using snakeyaml events.
                For the first element in the yaml a TaskOrAction is created
                using TaskOrActionBuilder 
                For each Element following actions tag a new TaskOrAction is created#
                For all other tags setProperty of TaskOrAction is called
                
                 It uses a stack to store the created TaskOrAction.
                """;

        String command = """
                implement
                public class YamlParser {
                        public void parse(Reader reader) {
                }
                Output exactly one compilable Java class and nothing else.
                """; */
        //String result = new CallLLM().call(prompt + classes + command);
        //for() {

        //}
        //System.out.println(prompt);

        /*
        correct
            error alle infos fehler in compiler finden (zeile)

            bei java to model  java code als commentar

         */

        var stopCommand = " Output exactly one compilable Java class and nothing else";

        var fileOutput = new FileOutput();
        var dir = Paths.get("/Users/thomas/workspace/agentelf/src/main/java/dev/agentelf/yaml/builderstate/");

        var list =  new BuilderStateFactory().create().toClassNameAndTextList();
        for(ClassNameAndText cl : list) {
            String result = new CallLLM().call( classes + cl.getText() + stopCommand);
            fileOutput.writeFile(result, cl.getName() + ".java" , dir);
            //System.out.println(classes + cl.getText() + stopCommand);
        }
    }

}
