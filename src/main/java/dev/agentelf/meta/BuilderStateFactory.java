package dev.agentelf.meta;

import dev.agentelf.model.Aggregate;
import dev.agentelf.model.Entity;
import dev.agentelf.model.Link;

import java.util.LinkedList;
import java.util.List;

public class BuilderStateFactory {

    /**
     *     BuilderState addAction(String name);
     *     BuilderState addProperty(String name, String value);
     *     BuilderState addSequence(String name);
     *     BuilderState addScalarToSequence(String name);
     * @return
     */

    public Aggregate create() {
        List<Entity> result = new LinkedList<>();

        var initial = new Entity("BuilderStateInitial","");
        var node = new Entity("BuilderStateNode","");
        var nodeList = new Entity("BuilderStateNodeList","");
        var property = new Entity("BuilderStateProperty","");


        initial.getOutgoing().add(new Link("addAction","", node ));
        node.getOutgoing().add(new Link("addProperty","", property ));
        node.getOutgoing().add(new Link("addAction","", node ));
        node.getOutgoing().add(new Link("addSequence","", nodeList ));
        nodeList.getOutgoing().add(new Link("addScalarToSequence","", nodeList ));


        result.add(initial);
        result.add(node);
        result.add(nodeList);
        result.add(property);

        return new Aggregate("{{name}} implements BuilderState",
                "all other methods throw not implemented runtime exception",
                "{{name}} creates a {{targetName}}" , result);
    }

}
