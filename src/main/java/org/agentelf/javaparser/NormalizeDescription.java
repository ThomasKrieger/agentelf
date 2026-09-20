package org.agentelf.javaparser;

public class NormalizeDescription {

    /**
     * Normalizes the description by ensuring it ends with either a semicolon either a closing brace.
     * If it does not, a semicolon is appended.
     *
     * @param description The string to normalize.
     * @return The normalized string.
     */
    public String normalize(String description) {
        if (description == null || description.isEmpty()) {
            return description;
        }

        // Check if description ends with ; either } (allowing for trailing whitespace)
        if (!description.matches(".*[;}]\\s*$")) {
            return description + ";";
        }

        return description;
    }
}