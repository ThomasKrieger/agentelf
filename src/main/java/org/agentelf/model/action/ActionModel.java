package org.agentelf.model.action;

import java.util.List;

public record ActionModel(List<ActionVariable> variables,
                          List<Action> actions) {
}
