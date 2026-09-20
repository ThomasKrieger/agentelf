package org.agentelf.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.agentelf.model.unittest.UnitTest;

import java.util.List;

/**
 * Type represents both a class fields records
 * So extends is only available for classes, but I think this
 * does not justify the creation of two types
 *
 */
public record Type(String name,
                   String documentation,
                   List<String> annotations,
                   List<Field> fields,
                   List<Method> methods,
                   @JsonProperty("implements") List<String> implementsInterfaces,
                   UnitTest unitTest)  implements AbstractType {
}
