package org.agentelf.mustache;


import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.agentelf.model.source.AbstractTypeSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;

@Component
public class ApplyTemplate {

    private final MustacheFactory mustacheFactory;

    public ApplyTemplate(MustacheFactory mustacheFactory) {
        this.mustacheFactory = mustacheFactory;
    }

    public String applyToIntermediateType(AbstractTypeSource type) throws IOException {
      return apply(type, "intermediateTypeToText.mustache");
   }

   public String apply(Object context, String templateName) throws IOException {
       Mustache mustache = mustacheFactory.compile( templateName);
       StringWriter stringWriter = new StringWriter();
       mustache.execute(stringWriter, context);
       return stringWriter.toString();
   }

}
