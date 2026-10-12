package org.agentelf.llm;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class CallGeminiList {

    private final List<CallLLM> callLLMList;

    public CallGeminiList() {
        List<ModelNameAndSize> modelList = new ArrayList<>();
        modelList.add(new ModelNameAndSize("gemini-3.8-flash" , 300 ));
        //modelList.add(new ModelNameAndSize("gemini-3.6-flash" , 300 ));


        this.callLLMList = new ArrayList<>();
        String apiKey = System.getenv("GEMINI_API_KEY");
        if(apiKey != null && ! apiKey.isEmpty()) {
            for(ModelNameAndSize modelNameAndSize : modelList) {
                callLLMList.add(new CallGemini(apiKey,modelNameAndSize));
            }
        }
    }


}
