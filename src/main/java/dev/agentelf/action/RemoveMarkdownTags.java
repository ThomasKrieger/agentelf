package dev.agentelf.action;

/**
 * RemoveMarkdownTags removes all lines from llmResponse
 * starting with ``` using regexp  and returns the new llmResponse
 */
public class RemoveMarkdownTags {

    public String removeMarkdownTags(String llmResponse) {
        if (llmResponse == null) {
            return null;
        }
        // Matches lines starting with ``` at the beginning of the line or after a newline
        // Uses (?m) for multiline mode so ^ matches the start of a line
        return llmResponse.replaceAll("(?m)^```.*$", "");
    }
}