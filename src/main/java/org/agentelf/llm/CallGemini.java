package org.agentelf.llm;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class CallGemini implements CallLLM {

    private final String apiKey;
    private final String modelName;
    private final int size;

    public CallGemini(String apiKey, ModelNameAndSize modelNameAndSize) {
        this.apiKey = apiKey;
        this.modelName = modelNameAndSize.name();
        this.size = modelNameAndSize.size();
    }

    public String call(String prompt) {
        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .build();
        System.out.println(apiKey);
        System.out.println(modelName);
        ChatResponse response = model.chat(ChatRequest.builder().messages(UserMessage.from(prompt)).build());
        System.out.println(response.tokenUsage());
        System.out.println(response.aiMessage().text());
        return response.aiMessage().text();
    }

    @Override
    public int size() {
        return size;
    }

}
