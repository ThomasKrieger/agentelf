package org.agentelf.model.generate;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class JavaInterface {

    private String name;
    private String documentation;
    private final List<JavaInterface> extendsInterface = new ArrayList<>();

}
