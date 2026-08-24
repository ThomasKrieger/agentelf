package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.type.Function;
import org.agentelf.model.type.TypeModel;
import org.agentelf.model.type.Type;
import org.agentelf.model.intermediate.AbstractTypeIntermediate;
import org.agentelf.model.intermediate.ClassIntermediate;
import org.agentelf.model.intermediate.MethodIntermediate;
import org.agentelf.model.unittest.UnitTestLLM;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class FunctionalModelToIntermediateTypeList {

    @Action(arguments = {"model"},
            returnVariable = "intermediateTypeList")
    public List<AbstractTypeIntermediate>  functionalModelTointermediateTypeList(TypeModel model) {
        List<AbstractTypeIntermediate> intermediateTypeList = new LinkedList<>();
        for(Type type : model.classes()) {
            ClassIntermediate classIntermediate = new ClassIntermediate();
            classIntermediate.setName(type.name());
            classIntermediate.setPackageName(model.packageName());
            if(type.unitTest() == null) {
                classIntermediate.setUnitTest(new UnitTestLLM());
            } else {
                classIntermediate.setUnitTest(type.unitTest());
            }
            intermediateTypeList.add(classIntermediate);
            for(String field :  type.fields()) {
                classIntermediate.addField(field);
            }
            for(Function function :  type.methods()) {
                MethodIntermediate method = classIntermediate.addMethodWithDocumentation(function.declaration(), function.documentation());
                method.setPrompt(function.prompt());
            }
        }
        return intermediateTypeList;
    }

}
