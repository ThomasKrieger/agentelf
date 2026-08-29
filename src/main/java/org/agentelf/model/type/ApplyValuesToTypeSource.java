package org.agentelf.model.type;

import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.MethodSource;
import org.agentelf.model.unittest.UnitTestLLM;

public class ApplyValuesToTypeSource {

    public void applyValues(AbstractType type, AbstractTypeSource typeSource) {
        typeSource.setDocumentation(type.documentation());
        typeSource.setName(type.name());
        if(type.annotations() != null) {
            typeSource.setAnnotations(type.annotations());
        }
        if(type.unitTest() == null) {
            typeSource.setUnitTest(new UnitTestLLM());
        } else {
            typeSource.setUnitTest(type.unitTest());
        }
        if(type.methods() != null) {
            for(Method function :  type.methods()) {
                MethodSource method = typeSource.addMethodWithDocumentation(function.declaration(), function.documentation());
                method.setPrompt(function.prompt());
                if(function.annotations() != null) {
                    method.setAnnotations(function.annotations());
                }
            }
        }
    }
}
