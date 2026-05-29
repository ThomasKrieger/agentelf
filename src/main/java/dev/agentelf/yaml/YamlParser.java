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

public class YamlParser implements TaskOrActionParent {

    private final TaskOrActionBuilder builder;
    private TaskOrAction task;

    public YamlParser(TaskOrActionBuilder builder) {
        this.builder = builder;
    }

    public TaskOrAction parse(Reader reader) {
        Parser parser = new ParserImpl(new StreamReader(reader),new LoaderOptions());
        Deque<YamlParserState> stack = new ArrayDeque<>();

        while (parser.peekEvent() != null) {
            Event event = parser.getEvent();
            System.out.println(event);
            if (event instanceof ScalarEvent scalarEvent) {
                String currentValue = scalarEvent.getValue();
                stack.peek().processScalarEvent(currentValue,builder);
            } else if (event instanceof MappingEndEvent
                    || event instanceof SequenceEndEvent) {
                stack.pop();
            }  else if (event instanceof MappingStartEvent) {
                if(stack.isEmpty()) {
                    stack.push(new StateAction(this));
                } else {
                    stack.push(stack.peek().processMappingStart());
                }
            } else if(event instanceof SequenceStartEvent) {
                stack.push(stack.peek().processSequenceStart());
            }
            else if(event instanceof DocumentStartEvent
                    || event instanceof DocumentEndEvent) {
                // ignore Document Start And End
            }
        }
        return task;
    }

    @Override
    public void addTask(TaskOrAction task) {
        this.task = task;
    }
}