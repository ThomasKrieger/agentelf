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
                .returnType(typeRepo.getForSimpleName(returnTypeName));
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
                    .type(null)
                    .name(identifiers.getFirst().getText())
                    .build();
        }
        return FunctionArgument.builder()
                .type(typeRepo.getForSimpleName(identifiers.get(0).getText()))
                .name(identifiers.get(1).getText())
                .build();
    }



}
