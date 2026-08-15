package org.agentelf.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplyTemplateTest {

    @Test
    public void testApplyTrue() throws IOException {
        ApplyTemplate applyTemplate = new ApplyTemplate(new DefaultMustacheFactory("template" ));
        ContextGuineaPig context = new ContextGuineaPig();
        context.setTest(true);

        String result = applyTemplate.apply(context, "test.mustache");

        assertEquals("true", result.trim());
    }

    @Test
    public void testApplyFalse() throws IOException {
        ApplyTemplate applyTemplate = new ApplyTemplate(new DefaultMustacheFactory("template" ));
        ContextGuineaPig context = new ContextGuineaPig();
        context.setTest(false);

        String result = applyTemplate.apply(context, "test.mustache");

        assertEquals("false", result.trim());
    }
}