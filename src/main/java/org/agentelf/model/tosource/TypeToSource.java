package org.agentelf.model.tosource;

import lombok.Data;
import org.agentelf.handle.MethodHandle;
import org.agentelf.handle.ReferenceTypeHandle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public  class TypeToSource {

   private ReferenceTypeHandle referenceTypeHandle;
   private String typePrompt;
   private final Map<MethodHandle,String> methodHandleToPrompt = new HashMap<>();
   private String source;

   public Optional<String> createPrompt() {
      if(methodHandleToPrompt.isEmpty()) {
         return Optional.empty();
      }
      StringBuilder builder = new StringBuilder();
      for(Map.Entry<MethodHandle,String> elem : methodHandleToPrompt.entrySet()) {
         builder.append("implement: " + elem.getKey());
         builder.append(System.lineSeparator());
         builder.append(elem.getValue());
         builder.append(System.lineSeparator());
      }
      return Optional.of(builder.toString());
   }

}
