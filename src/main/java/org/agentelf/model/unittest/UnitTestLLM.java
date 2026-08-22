package org.agentelf.model.unittest;

import lombok.Getter;
import org.agentelf.model.intermediate.AbstractTypeIntermediate;
import org.agentelf.mustache.ApplyTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class UnitTestLLM implements UnitTest {

    @Getter
    private String prompt;

    public UnitTestLLM() {
    }

    public UnitTestLLM(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public Optional<String> getPrompt(ApplyTemplate applyTemplate, AbstractTypeIntermediate type) throws IOException {
        return Optional.of(buildPrompt(applyTemplate , type));
    }

    @Override
    public UnitTest addPrompt(String prompt) {
        if(this.prompt == null) {
            this.prompt = prompt;
        } else {
            StringWriter stringWriter = new StringWriter();
            PrintWriter result = new PrintWriter(stringWriter);
            result.println(prompt);
            result.println(this.prompt );
            this.prompt = stringWriter.toString();
        }
        return this;
    }

    private String buildPrompt(ApplyTemplate applyTemplate,AbstractTypeIntermediate type) {
        try {
            if(prompt == null) {
                return applyTemplate.apply(type, "promptUnitTest.mustache");
            }  else {
                StringWriter stringWriter = new StringWriter();
                PrintWriter result = new PrintWriter(stringWriter);
                result.println(prompt);
                result.println(applyTemplate.apply(type, "promptUnitTest.mustache"));
                return stringWriter.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
