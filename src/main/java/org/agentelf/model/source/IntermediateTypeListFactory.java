package org.agentelf.model.source;

import java.util.List;

public class IntermediateTypeListFactory {

    public static List<AbstractTypeSource> create() {
        SourceTypeListBuilder builder = new SourceTypeListBuilder();

        ClassSource parseMethodClass = builder.addClass("org.agentelf.javaparser", "ParseField");
        parseMethodClass.addMethodWithDocumentation("TypeAndName parseField(String description);" ,
                "Parse description with StaticJavaParser.parseBodyDeclaration return TypeAndName");
        parseMethodClass.addUnitTestPrompt("""
                use the following descriptions as test data:
                   int i;
                   String name;
                   Parser test;
                """);

       // builder.addClass("org.agentelf.javaparser", "ParseField");


        return builder.build();
    }

}
