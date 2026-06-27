package org.agentelf.plantuml;

import net.atmp.CucaDiagram;
import net.sourceforge.plantuml.abel.Entity;
import net.sourceforge.plantuml.abel.Link;
import net.sourceforge.plantuml.core.Diagram;
import net.sourceforge.plantuml.teavm.PSystemBuilder2;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class DiagramReaderTest {

    @Test
    public void readDiagram() throws IOException {
        try (InputStream in = getClass().getClassLoader()
                .getResourceAsStream("plantuml/contains.puml")) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8))) {

                String[] lines =  reader.lines().toArray(String[]::new);
                Diagram diagram = PSystemBuilder2.getInstance().createDiagram(lines);
                System.out.println(diagram.getClass());

                CucaDiagram cucaDiagram = (CucaDiagram) diagram;
                for(Entity entity :  cucaDiagram.leafs()) {
                    System.out.println(entity.getName());
                    System.out.println(entity.getLeafType());
                }

                for(Link link :  cucaDiagram.getLinks()) {
                    System.out.println(link.getType().getLinkTypeName());
                    System.out.println(link.getEntity1().getName());
                    System.out.println(link.getEntity2().getName());
                }
            }
        }
    }

}
