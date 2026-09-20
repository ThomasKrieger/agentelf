package org.agentelf.model.functional;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.TypeName;

public class FunctionalTypeToJavapoetType {

    /**
     * search the type in the following places:
     * 1) in the usedFromPackageName
     * 2) in imports
     * 3) in java.lang either primitive
     * 4) in other jars which are part of the project
     *
     */
    public TypeName toJavapoetType(String usedFromPackageName, String type) {
        return  ClassName.get("java.lang",type);
    }

}
