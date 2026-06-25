package org.agentelf.model.generate;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Data
public class JavaType implements NamedElement, WithDocumentation, ExternalOrJavaType {

    private String name;
    private String documentation;
    private String declaration;

    /**
     * "class" or "interface"
     */
    private String type;

    private final List<JavaField> fields = new ArrayList<>();
    private final List<JavaMethod> methods = new ArrayList<>();
    private final List<ExternalOrJavaType> extendsTypes = new ArrayList<>();
    private final List<ExternalOrJavaType> uses = new ArrayList<>();


    public void addField(JavaField field) {
        fields.add(field);
    }

    public void addMethod(JavaMethod method) {
        methods.add(method);
    }

    public void addExtends(ExternalOrJavaType type) {
        extendsTypes.add(type);
    }

    public void addUses(ExternalOrJavaType type) {
        uses.add(type);
    }

    @Override
    public Object onExternalClass(Function<ExternalType, Object> onExternal) {
        return null;
    }

    @Override
    public Object onGeneratedClass(Function<JavaType, Object> onGenerated) {
        return onGenerated.apply(this);
    }
}
