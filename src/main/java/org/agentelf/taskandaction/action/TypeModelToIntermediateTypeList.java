package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.source.*;
import org.agentelf.model.type.Method;
import org.agentelf.model.type.TypeModel;
import org.agentelf.model.type.Type;
import org.agentelf.model.unittest.UnitTestLLM;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TypeModelToIntermediateTypeList {

    @Action(arguments = {"model"},
            returnVariable = "intermediateTypeList")
    public List<AbstractTypeSource> typeModelTointermediateTypeList(TypeModel model) {
        List<AbstractTypeSource> intermediateTypeList = new LinkedList<>();
        for(Type type : model.classes()) {
            ClassSource classIntermediate = new ClassSource();
            classIntermediate.setDocumentation(type.documentation());
            classIntermediate.setName(type.name());
            classIntermediate.setPackageName(model.packageName());
            if(type.unitTest() == null) {
                classIntermediate.setUnitTest(new UnitTestLLM());
            } else {
                classIntermediate.setUnitTest(type.unitTest());
            }
            intermediateTypeList.add(classIntermediate);
            if(type.fields() != null) {
                for(String field :  type.fields()) {
                    classIntermediate.addField(field);
                }
            }

            if(type.methods() != null) {
                for(Method function :  type.methods()) {
                    MethodSource method = classIntermediate.addMethodWithDocumentation(function.declaration(), function.documentation());
                    method.setPrompt(function.prompt());
                }
            }

            if(type.implementsInterfaces() != null) {
                for(String implement : type.implementsInterfaces()) {
                    classIntermediate.addImplements(TypeDescriptionSource.create(implement));
                }
            }

        }
        return intermediateTypeList;
    }

}
