package org.agentelf.javaparser;


import com.github.javaparser.ast.type.Type;
import org.agentelf.model.intermediate.ReferenceTypeDescriptionIntermediate;
import org.agentelf.model.intermediate.TypeDescriptionIntermediate;

public class JavaParserToIntermediateTyp {

    public TypeDescriptionIntermediate map(Type type) {
        ReferenceTypeDescriptionIntermediate referenceTypeDescriptionIntermediate = new ReferenceTypeDescriptionIntermediate("" , type.asString());
        return referenceTypeDescriptionIntermediate;
    }

}
