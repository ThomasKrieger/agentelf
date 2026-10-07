package org.agentelf.model.functional;

import org.agentelf.model.function.Function;
import org.agentelf.model.function.FunctionDeclarationParser;
import org.agentelf.type.TypeRepo;

public record FunctionWithPromptFunctional(String declaration,
                                           String prompt,
                                           String documentation) {

    public Function toFunction(String defaultPackage, TypeRepo typeRepo) {
        FunctionDeclarationParser parser = new ParserFactory().create(declaration);
        Function.FunctionBuilder builder = new CreateFunction(typeRepo,defaultPackage).visitFunctionDeclaration(parser.functionDeclaration());
        builder.prompt(prompt);
        builder.documentation(documentation);
        return builder.build();
    }

}
