package org.agentelf.model.functional;

import org.agentelf.model.function.Function;
import org.agentelf.model.function.FunctionDeclarationLexer;
import org.agentelf.model.function.FunctionDeclarationParser;
import org.agentelf.type.TypeRepo;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public record FunctionWithPromptFunctional(String declaration,
                                           String prompt,
                                           String documentation) {

    public Function toFunction(TypeRepo typeRepo) {
        CharStream chars = CharStreams.fromString(declaration);
        FunctionDeclarationLexer lexer =
                new FunctionDeclarationLexer(chars);
        CommonTokenStream tokens =
                new CommonTokenStream(lexer);
        FunctionDeclarationParser parser =
                new FunctionDeclarationParser(tokens);
        Function.FunctionBuilder builder = new CreateFunction(typeRepo).visitFunctionDeclaration(parser.functionDeclaration());
        builder.prompt(prompt);
        builder.documentation(documentation);
        return builder.build();
    }

}
