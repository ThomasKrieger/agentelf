package org.agentelf.inheritancegraph;

import lombok.Getter;
import org.agentelf.handle.MethodHandle;

import java.util.HashMap;
import java.util.Map;

public class MethodsToBeImplemented {

    @Getter
    private final Map<MethodHandle,String> methodHandleToPrompt = new HashMap<>();

    public void add(MethodHandle methodHandle , String prompt) {
        methodHandleToPrompt.put(methodHandle,prompt);
    }


}
