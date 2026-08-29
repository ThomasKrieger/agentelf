package org.agentelf.model.type;

import org.agentelf.model.unittest.UnitTest;

import java.util.List;

public interface AbstractType {

    String name();
    String documentation();
    List<String> annotations();
    List<Method> methods();
    UnitTest unitTest();

}
