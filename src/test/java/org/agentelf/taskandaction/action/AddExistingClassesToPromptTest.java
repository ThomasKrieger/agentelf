
package org.agentelf.taskandaction.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.agentelf.model.source.AbstractTypeSource;
import org.agentelf.model.source.SourceModel;
import org.agentelf.type.ReferenceTypeRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for AddExistingClassesToPrompt.
 */
public class AddExistingClassesToPromptTest {

    private AddExistingClassesToPrompt action;
    private ReferenceTypeRepo referenceTypeRepo;
    private SourceModel sourceModel;

    @BeforeEach
    void setUp() {
        action = new AddExistingClassesToPrompt();
        referenceTypeRepo = mock(ReferenceTypeRepo.class);
        sourceModel = mock(SourceModel.class);
    }

    @Test
    void testAddExistingClassesToPrompt_NullInputs() {
        // Case: All null
        assertEquals("", action.addExistingClassesToPrompt(null, null, null));

        // Case: Prompt provided, others null
        assertEquals("Initial Prompt", action.addExistingClassesToPrompt("Initial Prompt", null, null));

        // Case: Model provided, Repo null
        assertEquals("Prompt", action.addExistingClassesToPrompt("Prompt", sourceModel, null));
    }

    @Test
    void testAddExistingClassesToPrompt_EmptyModel() {
        when(sourceModel.getAllTypes()).thenReturn(Collections.emptyList());
        
        String result = action.addExistingClassesToPrompt("Start", sourceModel, referenceTypeRepo);
        
        assertEquals("Start", result);
    }

    @Test
    void testAddExistingClassesToPrompt_SuccessfulAppend() {
        AbstractTypeSource typeSource = mock(AbstractTypeSource.class);
        when(sourceModel.getAllTypes()).thenReturn(List.of(typeSource));
        when(typeSource.getAllUsedTypes()).thenReturn(Set.of("ServiceA", "ServiceB"));

        when(referenceTypeRepo.lookup("ServiceA")).thenReturn(Optional.of("public class ServiceA {}"));
        when(referenceTypeRepo.lookup("ServiceB")).thenReturn(Optional.of("public class ServiceB {}"));

        String result = action.addExistingClassesToPrompt("Original prompt", sourceModel, referenceTypeRepo);

        // Verify content. Note: Order in Set iteration might vary, so check for presence of both.
        // The implementation appends them separated by newlines.
        assert(result.contains("Original prompt"));
        assert(result.contains("public class ServiceA {}"));
        assert(result.contains("public class ServiceB {}"));
        assert(result.split("\n").length >= 3);
    }

    @Test
    void testAddExistingClassesToPrompt_Deduplication() {
        AbstractTypeSource type1 = mock(AbstractTypeSource.class);
        AbstractTypeSource type2 = mock(AbstractTypeSource.class);
        
        when(sourceModel.getAllTypes()).thenReturn(List.of(type1, type2));
        
        // Both types use "CommonType"
        when(type1.getAllUsedTypes()).thenReturn(Set.of("CommonType"));
        when(type2.getAllUsedTypes()).thenReturn(Set.of("CommonType"));

        when(referenceTypeRepo.lookup("CommonType")).thenReturn(Optional.of("class CommonType {}"));

        String result = action.addExistingClassesToPrompt("", sourceModel, referenceTypeRepo);

        // "class CommonType {}" should only appear once
        assertEquals("class CommonType {}", result.trim());
    }

    @Test
    void testAddExistingClassesToPrompt_TypeNotFoundInRepo() {
        AbstractTypeSource typeSource = mock(AbstractTypeSource.class);
        when(sourceModel.getAllTypes()).thenReturn(List.of(typeSource));
        when(typeSource.getAllUsedTypes()).thenReturn(Set.of("UnknownType"));

        // Repo returns empty
        when(referenceTypeRepo.lookup("UnknownType")).thenReturn(Optional.empty());

        String result = action.addExistingClassesToPrompt("Existing", sourceModel, referenceTypeRepo);

        assertEquals("Existing", result);
    }

    @Test
    void testAddExistingClassesToPrompt_NullUsedTypes() {
        AbstractTypeSource typeSource = mock(AbstractTypeSource.class);
        when(sourceModel.getAllTypes()).thenReturn(List.of(typeSource));
        // getAllUsedTypes returns null
        when(typeSource.getAllUsedTypes()).thenReturn(null);

        String result = action.addExistingClassesToPrompt("Prompt", sourceModel, referenceTypeRepo);

        assertEquals("Prompt", result);
    }

    @Test
    void testAddExistingClassesToPrompt_EmptyPromptStart() {
        AbstractTypeSource typeSource = mock(AbstractTypeSource.class);
        when(sourceModel.getAllTypes()).thenReturn(List.of(typeSource));
        when(typeSource.getAllUsedTypes()).thenReturn(Set.of("TypeA"));

        when(referenceTypeRepo.lookup("TypeA")).thenReturn(Optional.of("SourceA"));

        // Starting with empty string prompt
        String result = action.addExistingClassesToPrompt("", sourceModel, referenceTypeRepo);

        assertEquals("SourceA", result);
    }
}
