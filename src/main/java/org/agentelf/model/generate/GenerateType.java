package org.agentelf.model.generate;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Data
public class GenerateType implements NamedElement, WithDocumentation, ExternalOrGenerateType {

    private String name;
    private String documentation;
    private String declaration;

    /**
     * "class" or "interface"
     */
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

    @Override
    public Object onExternalClass(Function<ExternalType, Object> onExternal) {
        return null;
    }

    @Override
    public Object onGenerateClass(Function<GenerateType, Object> onGenerated) {
        return onGenerated.apply(this);
    }
}
