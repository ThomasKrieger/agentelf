package org.agentelf.llm;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class CallLLMList {

    private final List<CallLLM> callLLMList;

    public CallLLMList(CallGeminiList callGeminiList) {
        this.callLLMList = new ArrayList<>();
        callLLMList.addAll(callGeminiList.getCallLLMList());
    }

    public CallLLM large() {
        callLLMList.sort(new Comparator<CallLLM>() {
            @Override
            public int compare(CallLLM left, CallLLM rigth) {
                return Integer.compare(left.size(),rigth.size());
            }
        });
        return callLLMList.getFirst();
    }

    public CallLLM small() {
        callLLMList.sort(new Comparator<CallLLM>() {
            @Override
            public int compare(CallLLM left, CallLLM rigth) {
                return Integer.compare(rigth.size(),left.size());
            }
        });
        return callLLMList.getFirst();
    }

}
