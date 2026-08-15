package org.agentelf.model.intermediate;

import org.agentelf.mustache.ApplyTemplate;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class UnitTestLLMIntermediate implements UnitTestIntermediate {

    private String prompt;

    public UnitTestLLMIntermediate() {

    }

    public UnitTestLLMIntermediate(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public Optional<String> getPrompt(ApplyTemplate applyTemplate,AbstractTypeIntermediate type) throws IOException {
        return Optional.of(buildPrompt(applyTemplate , type));
    }

    @Override
    public UnitTestIntermediate addPrompt(String prompt) {
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
