package org.agentelf.llm;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class CallGemini {

    public String call(String prompt) {
        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_API_KEY"))
                .modelName("gemini-3.1-flash-lite")
                .build();
        ChatResponse response = model.chat(ChatRequest.builder().messages(UserMessage.from(prompt)).build());
        System.out.println(response.tokenUsage());
        return response.aiMessage().text();
    }

}
