package dev.agentelf.model.uml;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class MethodModel {

   private final String name;
   private final String returnType;
   private final List<VariabelModel> arguments = new LinkedList<>();

    public MethodModel(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
    }
}
