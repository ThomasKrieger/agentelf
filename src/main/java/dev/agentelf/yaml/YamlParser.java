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

        String currentValue = null;
        while (parser.peekEvent() != null) {
            Event event = parser.getEvent();

            if (event instanceof ScalarEvent scalarEvent) {
                currentValue = scalarEvent.getValue();
                if(!stack.isEmpty()) {
                    stack.peek().processScalarEvent(currentValue,builder);
                }
            } else if (event instanceof MappingEndEvent ) {
                if(! stack.isEmpty()) {
                    stack.pop();
                }
            }  else if (event instanceof MappingStartEvent) {
                if(stack.isEmpty()) {
                    if(currentValue != null) {
                        if("tasks".equals(currentValue)) {
                            stack.push(new StateActionList(stack.peek().taskOrAction()));
                        }
                        else {
                            stack.push(new StateAction(builder.create(currentValue)));
                        }

                    }
                } else {
                    stack.push(stack.peek().processMappingStart(currentValue,builder));
                }
            }
            else if(event instanceof DocumentStartEvent
                    || event instanceof DocumentEndEvent
                    || event instanceof SequenceStartEvent
                    || event instanceof SequenceEndEvent) {
                // ignore structural events
            }

        }
    }
}