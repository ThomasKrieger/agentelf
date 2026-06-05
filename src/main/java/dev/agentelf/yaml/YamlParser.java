package dev.agentelf.yaml;

import dev.agentelf.task.Task;
import dev.agentelf.yaml.builderstate.BuilderStateInitial;
import dev.agentelf.yaml.parserstate.ParserState;
import dev.agentelf.yaml.parserstate.ParserStateInitial;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.events.*;
import org.yaml.snakeyaml.parser.Parser;
import org.yaml.snakeyaml.parser.ParserImpl;
import org.yaml.snakeyaml.reader.StreamReader;

import java.io.Reader;

public class YamlParser implements ParserStateParent {

    private final TaskBuilder builder;
    private Task task;


    public YamlParser(TaskBuilder builder) {
        this.builder = builder;
    }

    public void parse(Reader reader) {
        Parser parser = new ParserImpl(new StreamReader(reader),new LoaderOptions());
        ParserState parserState = new ParserStateInitial(new BuilderStateInitial(builder,this),null);

        while (parser.peekEvent() != null) {
            Event event = parser.getEvent();
            System.out.println(event);

            if (event instanceof ScalarEvent scalarEvent) {
                String currentValue = scalarEvent.getValue();
                parserState = parserState.processScalarEvent(currentValue);
            } else if (event instanceof MappingStartEvent) {
                parserState = parserState.processMappingStart();
            } else if (event instanceof MappingEndEvent) {
                parserState = parserState.processMappingEnd();
            } else if(event instanceof SequenceStartEvent) {
                parserState = parserState.processSequenceStart();
            } else if(event instanceof SequenceEndEvent) {
                parserState = parserState.processSequenceEnd();
            }
            else if(event instanceof DocumentStartEvent
                    || event instanceof DocumentEndEvent) {
                // ignore Document Start And End
            }
        }

    }

    @Override
    public void setActionWrapper(Task task) {
        this.task = task;
    }
}