package org.agentelf.model.tosource;

import lombok.Data;
import org.agentelf.mustache.ApplyTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Data
public class MethodToSource {

   private String name;
   private String prompt;
   private String documentation;
   private String source;
   private boolean includePrompt;
   private TypeDescriptionToSource returnType;
   private List<String> annotations = new ArrayList<>();
   private final List<VariableDeclarationToSource> parameterList = new ArrayList<>();


   public void addParameter(TypeDescriptionToSource type, String name) {
      VariableDeclarationToSource parameter = new VariableDeclarationToSource();
      parameter.setType(type);
      parameter.setName(name);
      parameterList.add(parameter);
   }

   public String getPromptForTemplate() {
      if(source != null) {
         return "";
      }

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
