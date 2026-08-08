package org.agentelf.mustache;


import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.IOException;
import java.io.StringWriter;

public class ApplyTemplate {

    public String apply(Object context, String templateName) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory("template" );
        Mustache mustache = mf.compile( templateName);
        StringWriter stringWriter = new StringWriter();
        mustache.execute(stringWriter, context);
        return stringWriter.toString();
    }

}
