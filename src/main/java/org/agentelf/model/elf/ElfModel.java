package org.agentelf.model.elf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ElfModel(@JsonProperty("package") String packageName,
                       List<ElfType> records,
                       List<ElfType> classes) {
}
