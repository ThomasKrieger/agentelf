package org.agentelf.model.intermediate;

import java.util.List;

public class IntermediateTypeListFactory {

    public static List<AbstractTypeIntermediate> create() {
        IntermediateTypeListBuilder builder = new IntermediateTypeListBuilder();

        ClassIntermediate parseMethodClass = builder.addClass("org.agentelf.cli", "WriteLogbackXML");
        parseMethodClass.addMethodWithDocumentation("void writeLogbackXML(File targetDir) throws IOException;" ,
                "reads logbackTemplate.xml from the class path " +
                "and writes it as logback.xml to the targetDir");
        parseMethodClass.addUnitTestPrompt("use JUnit 5 @TempDir");

       // builder.addClass("org.agentelf.javaparser", "ParseField");


        return builder.build();
    }

}
