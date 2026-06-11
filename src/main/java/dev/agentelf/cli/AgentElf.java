package dev.agentelf.cli;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import dev.agentelf.file.FileOutput;
import dev.agentelf.llm.CallGemini;
import dev.agentelf.meta.CreateClassFactory;
import dev.agentelf.model.ClassAndPrompt;
import dev.agentelf.model.uml.ClassModel;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

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

        DefaultMustacheFactory mustacheFactory = new DefaultMustacheFactory();
        Mustache mustache = mustacheFactory.compile("template/initialClass.mustache");


        Writer writer = new StringWriter();
        PrintWriter print = new PrintWriter(writer);
        var command = " Implement the following class";
        var stopCommand = " Output exactly one compilable Java class and nothing else";

        var fileOutput = new FileOutput();
        var dir = Paths.get("/Users/thomas/workspace/agentelf/src/main/java/dev/agentelf/cli/");

        List<ClassAndPrompt> classAndPromptList = new LinkedList<>();

        var list = new CreateClassFactory().create();
        for(ClassModel elem : list.getClasses()) {
            print.println(command);
            mustache.execute(writer, elem);
            print.println();
            print.println(elem.getDocumentation());
            print.println(stopCommand);
            classAndPromptList.add(new ClassAndPrompt(""  , elem.getName(),  writer.toString()));
        }


        for(ClassAndPrompt classAndPrompt :classAndPromptList ) {
            System.out.println(classAndPrompt.getPrompt());
        }


        for(ClassAndPrompt cl : classAndPromptList) {
            String result = new CallGemini().call(  cl.getPrompt() );
            fileOutput.writeFile(result, cl.getName() + ".java" , dir);

           // System.out.println(result);
        }
    }

}
