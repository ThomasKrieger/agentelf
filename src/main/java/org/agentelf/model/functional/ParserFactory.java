package org.agentelf.model.functional;

import org.agentelf.model.function.FunctionDeclarationLexer;
import org.agentelf.model.function.FunctionDeclarationParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class ParserFactory {

    public FunctionDeclarationParser create(String declaration) {
        CharStream chars = CharStreams.fromString(declaration);
        FunctionDeclarationLexer lexer =
                new FunctionDeclarationLexer(chars);
        CommonTokenStream tokens =
                new CommonTokenStream(lexer);
        return new FunctionDeclarationParser(tokens);
    }

}
