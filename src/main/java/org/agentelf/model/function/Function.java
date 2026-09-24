package org.agentelf.model.function;

import lombok.Builder;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.type.Type;

import java.util.List;

@Builder
public record Function(String name,
                       String prompt,
                       String documentation,
                       List<FunctionArgument> arguments,
                       Type returnType) {

    public void addToSourceModelType(ToSourceModel toSourceModel)   {

    }

}
