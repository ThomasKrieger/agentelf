package dev.agentelf.cli;

import com.beust.jcommander.JCommander;

import java.io.IOException;

public class AgentElf {

    public static void main(String[] commandLine) throws IOException {
        new Initialize().initialize();

        var arguments = new Arguments();
        JCommander commandLineParser = JCommander.newBuilder()
                .addObject(arguments)
                .build();
        commandLineParser.parse(commandLine);
        if(arguments.getCommandAndPrompts().isEmpty()) {
            commandLineParser.usage();
            return;
        }

        System.out.println(arguments.getCommandAndPrompts().get(0));


    }

}
