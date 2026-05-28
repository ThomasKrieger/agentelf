package dev.agentelf.yaml;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.events.DocumentEndEvent;
import org.yaml.snakeyaml.events.DocumentStartEvent;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.events.MappingEndEvent;
import org.yaml.snakeyaml.events.MappingStartEvent;
import org.yaml.snakeyaml.events.ScalarEvent;
import org.yaml.snakeyaml.events.SequenceEndEvent;
import org.yaml.snakeyaml.events.SequenceStartEvent;
import org.yaml.snakeyaml.parser.Parser;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;

import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;

public class YamlParser {

    private final TaskOrActionBuilder builder;

    public YamlParser(TaskOrActionBuilder builder) {
        this.builder = builder;
    }

    public void parse(Reader reader) {
        Parser parser = new ParserImpl(new StreamReader(reader),new LoaderOptions());
        Deque<YamlParserState> stack = new ArrayDeque<>();

        while (parser.peekEvent() != null) {
            Event event = parser.getEvent();
            String previousValue = null;

            if (event instanceof ScalarEvent scalarEvent) {




                String value = scalarEvent.getValue();

                if(stack.isEmpty()) {
                    TaskOrAction root = builder.create(value);
                    stack.push(new StateAction(root));
                }
                else if ("tasks".equals(value)) {
                    stack.push(new StateActionList(stack.peek().taskOrAction()));
                }  else {
                    stack.peek().process(value,stack,builder);
                }
            } else if (event instanceof MappingEndEvent) {
                stack.pop();
            }  else if (event instanceof MappingStartEvent) {
                if(previousValue != null) {
                    if ("tasks".equals(previousValue)) {

                    }
                }
            }
            else if (event instanceof SequenceEndEvent
                    || event instanceof SequenceStartEvent
                    || event instanceof DocumentStartEvent
                    || event instanceof DocumentEndEvent) {
                // ignore structural events
            }

        }
    }
}