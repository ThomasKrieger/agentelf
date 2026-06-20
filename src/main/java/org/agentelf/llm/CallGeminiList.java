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
        modelList.add(new ModelNameAndSize("gemini-3.1-flash-lite" , 100 ));
        modelList.add(new ModelNameAndSize("gemini-3.5-flash" , 200 ));
        modelList.add(new ModelNameAndSize("gemini-3-flash-preview" , 300 ));

        this.callLLMList = new ArrayList<>();
        String apiKey = System.getenv("GEMINI_API_KEY");
        if(apiKey != null && ! apiKey.isEmpty()) {
            for(ModelNameAndSize modelNameAndSize : modelList) {
                callLLMList.add(new CallGemini(apiKey,modelNameAndSize));
            }
        }
    }


}
