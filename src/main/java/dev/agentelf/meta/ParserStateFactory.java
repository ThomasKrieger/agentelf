package dev.agentelf.meta;

import dev.agentelf.model.Aggregate;
import dev.agentelf.model.Entity;
import dev.agentelf.model.Link;

import java.util.LinkedList;
import java.util.List;

public class ParserStateFactory {

    /*
        ParserState processScalarEvent(String value);
    ParserState processMappingStart();
    ParserState processMappingEnd();
    ParserState processSequenceStart();
    ParserState processSequenceEnd();
     */

    public Aggregate create() {
        List<Entity> result = new LinkedList<>();

        var initial = new Entity("ParserStateInitial","processMappingEnd return the previous filed");
        var mappingStarted = new Entity("ParserStateMappingStarted","");
        var mapped = new Entity("ParserStateMapped","");
        var sequence = new Entity("ParserStateSequence","");

        initial.getOutgoing().add(new Link("processMappingStart","", mappingStarted ));
        mappingStarted.getOutgoing().add(new Link("processScalarEvent","", mapped ));


        mapped.getOutgoing().add(new Link("processScalarEvent","call addProperty on ActionBuilder and " +
                "use this for the new created ParserStateMappingStarted", mappingStarted ));
        mapped.getOutgoing().add(new Link("processMappingStart","call addAction on ActionBuilder and " +
                "use this for the new created ParserStateMappingStarted", mappingStarted ));
        mapped.getOutgoing().add(new Link("processSequenceStart","call addSequence on ActionBuilder and " +
                "use this for the new created ParserStateSequence", sequence ));

        sequence.getOutgoing().add(new Link("processScalarEvent","call addNode on ActionBuilder " +
                "return the same sequence", sequence ));
        sequence.getOutgoing().add(new Link("processMappingStart","", mappingStarted ));
        sequence.getOutgoing().add(new Link("processSequenceEnd","return previous", mapped ));

        result.add(initial);
        result.add(mappingStarted);
        result.add(mapped);
        result.add(sequence);

        return new Aggregate("{{name}} implements ParserState contains a final field ActionBuilder" +
                " and a final field previous of type ParserState and a constructor for both fields",
                "all other methods throw not implemented runtime exception",
                "{{name}} creates a {{targetName}}" , result);
    }

}
