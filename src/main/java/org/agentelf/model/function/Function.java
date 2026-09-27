package org.agentelf.model.function;

import com.palantir.javapoet.MethodSpec;
import lombok.Builder;
import org.agentelf.handle.MethodHandle;
import org.agentelf.handle.ReferenceTypeHandle;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.model.tosource.TypeToSource;
import org.agentelf.type.Type;

import javax.lang.model.element.Modifier;
import java.util.List;

@Builder
public record Function(String name,
                       String prompt,
                       String documentation,
                       List<FunctionArgument> arguments,
                       Type returnType) {

    public void addToSourceModelType(ToSourceModel toSourceModel)   {
        if(! arguments.isEmpty()) {
            FunctionArgument firstArgument = arguments.getFirst();
            for(ReferenceTypeHandle handle :  toSourceModel.getAllTypeHandles()) {
                TypeToSource model = toSourceModel.getType(handle);
                if(firstArgument.type().toHandle().equals(model.getReferenceTypeHandle())) {
                    model.getMethodHandleToPrompt().put(new MethodHandle(name), prompt);
                    MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(name).addModifiers(Modifier.ABSTRACT,Modifier.PUBLIC);
                    methodBuilder.returns(returnType.toJavapoet());
                    arguments.forEach(  arg-> arg.addToBuilder(methodBuilder));
                    model.getTypeSource().asBuilder().addMethod(methodBuilder.build());
                }
            }
        }
    }

}
