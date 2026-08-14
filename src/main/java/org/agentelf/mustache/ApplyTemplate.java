package org.agentelf.mustache;


import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.agentelf.model.intermediate.AbstractTypeIntermediate;

import java.io.IOException;
import java.io.StringWriter;

public class ApplyTemplate {

   public String applyToIntermediateType(AbstractTypeIntermediate type) throws IOException {
      return apply(type, "typeToText.mustache");
   }

   public String apply(Object context, String templateName) throws IOException {
       MustacheFactory mf = new DefaultMustacheFactory("template" );
       Mustache mustache = mf.compile( templateName);
       StringWriter stringWriter = new StringWriter();
       mustache.execute(stringWriter, context);
       return stringWriter.toString();
   }

}
