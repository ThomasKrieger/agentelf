package org.agentelf.model.intermediate;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MethodIntermediate {

   private String name;
   private String prompt;
   private String documentation;
   private boolean includePrompt;
   private TypeDescriptionIntermediate returnType;
   private final List<VariableDeclarationIntermediate> parameterList = new ArrayList<>();

   public void addParameter(TypeDescriptionIntermediate type, String name) {
      VariableDeclarationIntermediate parameter = new VariableDeclarationIntermediate();
      parameter.setType(type);
      parameter.setName(name);
      parameterList.add(parameter);
   }

   public String getPromptForTemplate() {
      if(includePrompt) {
         return prompt;
      }
      return "";
   }

}
