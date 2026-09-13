
package org.agentelf.taskandaction.action;

import org.agentelf.api.Action;
import org.agentelf.model.tosource.AbstractTypeToSource;
import org.agentelf.model.tosource.ToSourceModel;
import org.agentelf.type.ReferenceTypeRepo;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AddExistingClassesToPrompt {

    /**
     * Iterates over all types in the source model, identifies all types used within their 
     * methods and fields, looks up the source code for those types using the ReferenceTypeRepo, 
     * and appends found source code to the prompt.
     */
    @Action(arguments = {"prompt", "toSourceModel", "referenceTypeRepo"}, returnVariable = "prompt")
    public String addExistingClassesToPrompt(
            String prompt,
            ToSourceModel toSourceModel,
            ReferenceTypeRepo referenceTypeRepo
    ) {
        StringBuilder promptBuilder = new StringBuilder(prompt != null ? prompt : "");
        Set<String> processedTypes = new HashSet<>();

        if (toSourceModel != null && referenceTypeRepo != null) {
            for (AbstractTypeToSource typeSource : toSourceModel.getAllTypes()) {
                Set<String> usedTypes = typeSource.getAllUsedTypes();
                if (usedTypes != null) {
                    for (String usedTypeName : usedTypes) {
                        // Avoid redundant lookups and duplicates in the prompt
                        if (!processedTypes.contains(usedTypeName)) {
                            Optional<String> typeContent = referenceTypeRepo.lookup(usedTypeName);
                            if (typeContent.isPresent()) {
                                // Add a newline separator if the prompt already contains content
                                if (!promptBuilder.isEmpty()) {
                                    promptBuilder.append("\n");
                                }
                                promptBuilder.append(typeContent.get());
                                processedTypes.add(usedTypeName);
                            }
                        }
                    }
                }
            }
        }
        return promptBuilder.toString();
    }
}
