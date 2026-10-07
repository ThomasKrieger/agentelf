
package org.agentelf.model.activity;

import java.util.List;


public record Block(String name,
                    String predicate,
                    String java,
                    List<Statement> statements) implements Statement {

}
