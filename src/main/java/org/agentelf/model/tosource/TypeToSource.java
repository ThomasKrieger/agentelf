package org.agentelf.model.tosource;

import lombok.Data;
import org.agentelf.handle.MethodHandle;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.javaparser.JavaParserWrapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Data
public class TypeToSource {

   private final ReferenceTypeHandle referenceTypeHandle;
   private String typePrompt;
   private final Map<MethodHandle,String> methodHandleToPrompt = new HashMap<>();
   private TypeSource typeSource;

   public TypeToSource(ReferenceTypeHandle referenceTypeHandle) {
      this.referenceTypeHandle = referenceTypeHandle;
   }

   public boolean needsLLM(JavaParserWrapper javaParserWrapper) throws IOException {
       return ! javaParserWrapper.parse(typeSource.asString()).isAbstract();
   }

   public boolean needsTest(JavaParserWrapper javaParserWrapper) throws IOException {
      return ! javaParserWrapper.parse(typeSource.asString()).isAbstract();
   }

   public String getSourceAsString() {
      return typeSource.asString();
   }

}
