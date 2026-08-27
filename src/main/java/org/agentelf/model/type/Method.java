package org.agentelf.model.type;

import java.util.List;

public record Method(List<String> annotations,
                     String declaration,
                     String documentation,
                     String prompt,
                     String label) {
}
