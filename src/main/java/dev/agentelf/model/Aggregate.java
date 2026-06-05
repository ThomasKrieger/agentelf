package dev.agentelf.model;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.MustacheFactory;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;

public class Aggregate {

    private final String beginEntity;
    private final String endEntity;
    private final String linkText;
    private final List<Entity> entities;

    public Aggregate(String beginEntity,
                     String endEntity,
                     String linkText,
                     List<Entity> entities) {
        this.beginEntity = beginEntity;
        this.endEntity = endEntity;
        this.linkText = linkText;
        this.entities = entities;
    }

    public String getBeginEntity() {
        return beginEntity;
    }

    public String getEndEntity() {
        return endEntity;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public String getLinkText() {
        return linkText;
    }

    public List<ClassNameAndText> toClassNameAndTextList() {
        List<ClassNameAndText> result = new LinkedList<>();
        var mf = new DefaultMustacheFactory();
        var templateBeginEntity = mf.compile(new StringReader(beginEntity ), "beginEntity");
        var templateEndEntity = mf.compile(new StringReader(endEntity ), "endEntity");
        var templateLinkText = mf.compile(new StringReader(linkText ), "linkText");

        for(Entity entity : entities) {
            var stringWriter = new StringWriter();
            var printWriter = new PrintWriter(stringWriter);

            templateBeginEntity.execute(stringWriter,entity);
            printWriter.println();
            for(Link outgoing : entity.getOutgoing()) {
                if(outgoing.getDescription().isEmpty()) {
                    templateLinkText.execute(stringWriter,outgoing);
                } else {
                    printWriter.print(outgoing.getName() + " " + outgoing.getDescription());
                }

                printWriter.println();
            }

            templateEndEntity.execute(stringWriter,entity);

            result.add(new ClassNameAndText(entity.getName(),stringWriter.toString() ));
        }

        return result;
    }
}
