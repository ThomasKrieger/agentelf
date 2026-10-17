package org.agentelf.model.function;

import com.palantir.javapoet.CodeBlock;
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
            List<FunctionArgument> boundArguments = arguments.subList(1, arguments.size());
            FunctionArgument firstArgument = arguments.getFirst();
            for(ReferenceTypeHandle handle :  toSourceModel.getAllTypeHandles()) {
                TypeToSource model = toSourceModel.getType(handle);
                if(firstArgument.type().toHandle().equals(model.getReferenceTypeHandle())) {
                    MethodSpec.Builder methodBuilderForPrompt = createBuilder(boundArguments);
                    methodBuilderForPrompt.addCode(CodeBlock.of(""));

                    model.getMethodHandleToPrompt().put(new MethodHandle(
                            name,
                                    boundArguments.stream().map(FunctionArgument::type).map(Type::toHandle).toList(),
                            returnType.toHandle()),
                                    methodBuilderForPrompt.build() +
                                    System.lineSeparator() +
                                    prompt);

                    MethodSpec.Builder methodBuilder = createBuilder(boundArguments);
                    methodBuilder.addModifiers(Modifier.ABSTRACT);
                    model.getTypeSource().asBuilder().addMethod(methodBuilder.build());

                }
            }
        }
    }

    private MethodSpec.Builder createBuilder(List<FunctionArgument> boundArguments) {
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(name).addModifiers(Modifier.PUBLIC);
        methodBuilder.returns(returnType.toJavapoet());
        boundArguments.forEach(  arg-> arg.addToBuilder(methodBuilder));
        return methodBuilder;
    }


}
