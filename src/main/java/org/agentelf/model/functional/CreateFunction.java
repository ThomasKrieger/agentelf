package org.agentelf.model.functional;

import lombok.AllArgsConstructor;
import org.agentelf.model.function.Function;
import org.agentelf.model.function.FunctionArgument;
import org.agentelf.model.function.FunctionDeclarationParser;
import org.agentelf.type.ReferenceType;
import org.agentelf.type.Type;
import org.agentelf.type.TypeRepo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
public class CreateFunction {

    private final TypeRepo typeRepo;
    private final String currentPackage;

    public Function.FunctionBuilder visitFunctionDeclaration(
            FunctionDeclarationParser.FunctionDeclarationContext ctx) {
        String functionName = ctx.IDENTIFIER().getText();
        List<FunctionArgument> arguments =
                ctx.argumentList() == null
                        ? List.of()
                        : visitArgumentListInternal(ctx.argumentList());
        return Function.builder()
                .name(functionName)
                .arguments(arguments)
                .returnType(visitType(ctx.type()));
    }

    private List<FunctionArgument> visitArgumentListInternal(
            FunctionDeclarationParser.ArgumentListContext ctx) {
        List<FunctionArgument> result = new ArrayList<>();
        for (FunctionDeclarationParser.ArgumentContext argument
                : ctx.argument()) {

            result.add(visitArgumentInternal(argument));
        }
        return result;
    }

    private FunctionArgument visitArgumentInternal(
            FunctionDeclarationParser.ArgumentContext ctx) {

        if (ctx.IDENTIFIER() == null) {
            Type type = visitType(ctx.type());
            return FunctionArgument.builder()
                    .type(type)
                    .name(firstCharToLowerCase(type.simpleName()))
                    .build();
        }
        return FunctionArgument.builder()
                .type(visitType(ctx.type()))
                .name(ctx.IDENTIFIER().getText())
                .build();
    }

    public Type visitType(FunctionDeclarationParser.TypeContext typeContext) {
        Type type;
        if(typeContext.package_() == null) {
            type =  typeRepo.getForSimpleName(currentPackage, typeContext.IDENTIFIER().getText());
        } else {
            type =  new ReferenceType(typeContext.package_().getText(),
                    typeContext.IDENTIFIER().getText());
        }
        if(typeContext.generic() != null) {
            List<Type> generics = new LinkedList<>();
            for(FunctionDeclarationParser.TypeContext generic : typeContext.generic().type()) {
                generics.add(visitType(generic));
            }
            type.addGenerics(generics);
        }
        if(typeContext.array() != null) {
            type.setIsArray();
        }
        return type;
    }

    private String firstCharToLowerCase(String text) {
        return  Character.toLowerCase(text.charAt(0))
                + text.substring(1);
    }

}
