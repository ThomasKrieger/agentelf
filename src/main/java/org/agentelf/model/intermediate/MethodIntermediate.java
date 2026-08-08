package org.agentelf.model.intermediate;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MethodIntermediate {

   private String name;
   private String prompt;
   private String documentation;
   private final List<VariableDeclarationIntermediate> parameterList = new ArrayList<>();

}
