package org.agentelf.model.tosource;

import com.github.javaparser.ast.CompilationUnit;
import lombok.Data;
import org.agentelf.handle.MethodHandle;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.inheritancegraph.MethodsToBeImplemented;
import org.agentelf.javaparser.CompilationUnitWrapper;
import org.agentelf.javaparser.JavaParserWrapper;
import org.agentelf.type.ModelOrSource;
import org.agentelf.type.ReferenceTypeRepo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Data
public class TypeToSource implements ModelOrSource  {

   private final ReferenceTypeHandle referenceTypeHandle;
   private String typePrompt;
   private final Map<MethodHandle,String> methodHandleToPrompt = new HashMap<>();
   private TypeSource typeSource;

   public TypeToSource(ReferenceTypeHandle referenceTypeHandle) {
      this.referenceTypeHandle = referenceTypeHandle;
   }

   public Map<MethodHandle,String> getAllMethodsToBeImplemented(ReferenceTypeRepo referenceTypeRepo) {
      Map<MethodHandle,String> fromSuperTypes =  referenceTypeRepo.getAllMethodsToBeImplemented(referenceTypeHandle);
       fromSuperTypes.putAll(methodHandleToPrompt);
       return fromSuperTypes;
   }

   public boolean isAbstract(JavaParserWrapper javaParserWrapper) throws IOException {
       return  javaParserWrapper.parse(typeSource.asString()).isAbstract();
   }

   public boolean needsUnitTest(JavaParserWrapper javaParserWrapper) throws IOException {
      CompilationUnitWrapper cu = javaParserWrapper.parse(typeSource.asString());
      if(cu.isAbstract()) {
         return false;
      }
      return cu.declaresMethods();
   }

   public String getSourceAsString() {
      return typeSource.asString();
   }

   @Override
   public void uodate(CompilationUnit parse) {
      typeSource = new TypeSourceString(parse.toString());
   }

   @Override
   public void addToMethodsToBeImplemented(MethodsToBeImplemented methodsToBeImplemented) {
      methodHandleToPrompt.forEach(methodsToBeImplemented::add);
   }
}
