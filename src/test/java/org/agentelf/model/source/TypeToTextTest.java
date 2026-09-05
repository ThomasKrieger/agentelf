package org.agentelf.model.source;

import com.github.mustachejava.DefaultMustacheFactory;
import org.agentelf.mustache.ApplyTemplate;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;

import static org.agentelf.util.DiffText.assertTextEquals;

public class TypeToTextTest {

    @Test
    public void classToTextIncludePrompt() throws IOException {
        ClassSource classIntermediate = new ClassSource();
        classIntermediate.setPackageName("org.agentelf.model.intermediate");
        classIntermediate.setName("IntermediateClass");
        classIntermediate.setIncludePrompt(true);
        classIntermediate.setPrompt("this is a prompt");
        classIntermediate.addImplements(TypeDescriptionSource.create("Serializable"));
        classIntermediate.setAnnotations(Collections.singletonList("@Deprecated"));

        ApplyTemplate applyTemplate = new ApplyTemplate(new DefaultMustacheFactory("org/agentelf/initialresource/template"));
        String text = applyTemplate.applyToIntermediateType(classIntermediate);
        assertTextEquals("/model/source/classToTextIncludePrompt.txt" , text);
    }

    @Test
    public void interfaceToText() throws IOException {
        InterfaceSource interfaceSource = new InterfaceSource();
        interfaceSource.setPackageName("org.agentelf.model.intermediate");
        interfaceSource.setName("IntermediateClass");
        interfaceSource.addExtends(TypeDescriptionSource.create("Serializable"));

        ApplyTemplate applyTemplate = new ApplyTemplate(new DefaultMustacheFactory("org/agentelf/initialresource/template"));
        String text = applyTemplate.applyToIntermediateType(interfaceSource);
        assertTextEquals("/model/source/interfaceToText.txt" , text);
    }



}
