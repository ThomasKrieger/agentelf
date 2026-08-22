package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.elf.Function;
import org.agentelf.model.elf.ElfModel;
import org.agentelf.model.elf.ElfType;
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
    public List<AbstractTypeIntermediate>  functionalModelTointermediateTypeList(ElfModel model) {
        List<AbstractTypeIntermediate> intermediateTypeList = new LinkedList<>();
        for(ElfType type : model.classes()) {
            ClassIntermediate classIntermediate = new ClassIntermediate();
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
