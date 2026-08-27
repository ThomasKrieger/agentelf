package org.agentelf.model.source;

import lombok.Data;
import org.agentelf.mustache.ApplyTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Data
public class MethodSource {

   private String name;
   private String prompt;
   private String documentation;
   private boolean includePrompt;
   private TypeDescriptionSource returnType;
   private final List<VariableDeclarationSource> parameterList = new ArrayList<>();

   public void addParameter(TypeDescriptionSource type, String name) {
      VariableDeclarationSource parameter = new VariableDeclarationSource();
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

   public void addImplementMethodToPrompt(ApplyTemplate applyTemplate)  {
       try {
        if(prompt == null) {
           prompt = applyTemplate.apply(this, "promptImplementMethod.mustache");
        }  else {
           StringWriter stringWriter = new StringWriter();
           PrintWriter result = new PrintWriter(stringWriter);
           result.println(prompt);
           result.println(applyTemplate.apply(this, "promptImplementMethod.mustache"));
        }
       } catch (IOException e) {
          throw new RuntimeException(e);
       }
   }

}
