package org.agentelf.model.type;

import java.util.List;

public record Field(List<String> annotations,
                    String declaration) {
}
