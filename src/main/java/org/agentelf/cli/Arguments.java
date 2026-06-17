package org.agentelf.cli;

import com.beust.jcommander.Parameter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Arguments {

    @Parameter(description = "Command and one or more prompts prompts")
    private List<String> commandAndPrompts = new ArrayList<>();;

    @Parameter(names = { "-p", "--package" }, description = "The package name for the classes")
    private String packageName;

}
