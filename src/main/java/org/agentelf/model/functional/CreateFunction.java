package org.agentelf.model.functional;

import lombok.AllArgsConstructor;
import org.agentelf.model.function.Function;
import org.agentelf.model.function.FunctionArgument;
import org.agentelf.model.function.FunctionDeclarationParser;
import org.agentelf.type.TypeRepo;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CreateFunction {

    private final TypeRepo typeRepo;
    private final String currentPackage;

    public Function.FunctionBuilder visitFunctionDeclaration(
            FunctionDeclarationParser.FunctionDeclarationContext ctx) {
        String functionName = ctx.IDENTIFIER(0).getText();
        String returnTypeName =
                ctx.IDENTIFIER(ctx.IDENTIFIER().size() - 1).getText();
        List<FunctionArgument> arguments =
                ctx.argumentList() == null
                        ? List.of()
                        : visitArgumentListInternal(ctx.argumentList());
        return Function.builder()
                .name(functionName)
                .arguments(arguments)
                .returnType(typeRepo.getForSimpleName(currentPackage, returnTypeName));
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

    public FunctionArgument visitArgumentInternal(
            FunctionDeclarationParser.ArgumentContext ctx) {
        List<TerminalNode> identifiers = ctx.IDENTIFIER();
        if (identifiers.size() == 1) {
            return FunctionArgument.builder()
                    .type(typeRepo.getForSimpleName(currentPackage,identifiers.getFirst().getText()))
                    .name(Character.toLowerCase(identifiers.getFirst().getText().charAt(0)) +
                                    identifiers.getFirst().getText().substring(1))
                    .build();
        }
        return FunctionArgument.builder()
                .type(typeRepo.getForSimpleName(currentPackage, identifiers.get(1).getText()))
                .name(identifiers.get(0).getText())
                .build();
    }



}
