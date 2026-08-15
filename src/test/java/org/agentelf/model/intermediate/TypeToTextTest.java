package org.agentelf.model.intermediate;

import com.github.mustachejava.DefaultMustacheFactory;
import org.agentelf.mustache.ApplyTemplate;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TypeToTextTest {

    @Test
    public void classToTextIncludePrompt() throws IOException {
        ClassIntermediate classIntermediate = new ClassIntermediate();
        classIntermediate.setPackageName("org.agentelf.model.intermediate");
        classIntermediate.setName("IntermediateClass");
        classIntermediate.setIncludePrompt(true);
        classIntermediate.setPrompt("this is a prompt");

        ApplyTemplate applyTemplate = new ApplyTemplate(new DefaultMustacheFactory("org/agentelf/initialresource/template"));
        String text = applyTemplate.applyToIntermediateType(classIntermediate);
        System.out.println(text);

    }

}
