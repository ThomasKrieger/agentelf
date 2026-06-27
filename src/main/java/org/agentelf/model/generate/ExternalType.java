package org.agentelf.model.generate;

import lombok.Data;
import org.agentelf.model.common.NamedElement;

@Data
public class ExternalType implements NamedElement, ExternalOrGenerateType {

    private String name;

}
