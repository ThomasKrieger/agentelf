package org.agentelf.model.umlclass;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class UMLMethod {

   private final String name;
   private final String returnType;
   private final List<UMLFiled> arguments = new LinkedList<>();

    public UMLMethod(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
    }
}
