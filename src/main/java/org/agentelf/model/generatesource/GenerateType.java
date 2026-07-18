package org.agentelf.model.generatesource;

import lombok.Data;
import org.agentelf.model.common.NamedElement;
import org.agentelf.model.common.WithDocumentation;

import java.util.ArrayList;
import java.util.List;

@Data
public class GenerateType implements NamedElement, WithDocumentation, ExternalOrGenerateType {

    private String name;
    private String documentation;
    private String declaration;
    private String type;

    private final List<GenerateField> fields = new ArrayList<>();
    private final List<GenerateMethod> methods = new ArrayList<>();
    private final List<ExternalOrGenerateType> extendsTypes = new ArrayList<>();
    private final List<ExternalOrGenerateType> uses = new ArrayList<>();

    public void addField(GenerateField field) {
        fields.add(field);
    }

    public void addMethod(GenerateMethod method) {
        methods.add(method);
    }

    public void addExtends(ExternalOrGenerateType type) {
        extendsTypes.add(type);
    }

    public void addUses(ExternalOrGenerateType type) {
        uses.add(type);
    }

}
