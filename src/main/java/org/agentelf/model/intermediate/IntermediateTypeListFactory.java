package org.agentelf.model.intermediate;

import java.util.List;

public class IntermediateTypeListFactory {

    public static List<AbstractTypeIntermediate> create() {
        IntermediateTypeListBuilder builder = new IntermediateTypeListBuilder();

        ClassIntermediate parseMethodClass = builder.addClass("org.agentelf.javaparser", "ParseMethod");
        parseMethodClass.addMethodWithDocumentation(new ReferenceTypeDescriptionIntermediate("org.agentelf.model.intermediate", "MethodIntermediate") , "parseMethod" , "Uses StaticJavaParser parseBodyDeclaration to parse a string" +
                "containing a java method and returns a MethodIntermediate");


       // builder.addClass("org.agentelf.javaparser", "ParseField");



        return builder.build();
    }

}
