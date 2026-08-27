package org.agentelf.model.source;

import com.github.mustachejava.DefaultMustacheFactory;
import org.agentelf.mustache.ApplyTemplate;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.agentelf.util.DiffText.assertTextEquals;

public class TypeToTextTest {

    @Test
    public void classToTextIncludePrompt() throws IOException {
        ClassSource classIntermediate = new ClassSource();
        classIntermediate.setPackageName("org.agentelf.model.intermediate");
        classIntermediate.setName("IntermediateClass");
        classIntermediate.setIncludePrompt(true);
        classIntermediate.setPrompt("this is a prompt");

        ApplyTemplate applyTemplate = new ApplyTemplate(new DefaultMustacheFactory("org/agentelf/initialresource/template"));
        String text = applyTemplate.applyToIntermediateType(classIntermediate);
        assertTextEquals("/model/intermediate/classToTextIncludePrompt.txt" , text);
    }

}
