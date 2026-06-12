package dev.agentelf.cli;

import com.beust.jcommander.Parameter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Arguments {

    @Parameter(description = "Command and one or more prompts prompts")
    private List<String> commandAndPrompts = new ArrayList<>();;

}
