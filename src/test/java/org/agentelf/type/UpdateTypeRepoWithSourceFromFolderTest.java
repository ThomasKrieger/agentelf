
package org.agentelf.type;

import com.github.javaparser.ast.CompilationUnit;
import org.agentelf.handle.ReferenceTypeHandle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UpdateTypeRepoWithSourceFromFolderTest {

    @Test
    @SuppressWarnings("unchecked")
    void testUpdatePopulatesRepoFromDirectory(@TempDir Path tempDir) throws IOException {
        // Arrange: Create a temporary Java source file
        Path packagePath = tempDir.resolve("org/agentelf/test");
        Files.createDirectories(packagePath);
        Path javaFile = packagePath.resolve("TestEntity.java");
        
        String javaCode = 
            "package org.agentelf.test;\n" +
            "public class TestEntity {\n" +
            "    private String id;\n" +
            "}\n";
        
        Files.writeString(javaFile, javaCode);

        ReferenceTypeRepo mockRepo = mock(ReferenceTypeRepo.class);
        UpdateTypeRepoWithSourceFromFolder updater = new UpdateTypeRepoWithSourceFromFolder();

        // Act: Run the update logic on the temporary directory
        updater.update(mockRepo, tempDir.toString());

        // Assert: Capture arguments passed to putAll
        ArgumentCaptor<Map<String, Set<ReferenceTypeHandle>>> nameMapCaptor = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<Map<ReferenceTypeHandle, CompilationUnit>> sourceMapCaptor = ArgumentCaptor.forClass(Map.class);

        verify(mockRepo).putAll(nameMapCaptor.capture(), sourceMapCaptor.capture());

        Map<String, Set<ReferenceTypeHandle>> nameToHandle = nameMapCaptor.getValue();
        Map<ReferenceTypeHandle, CompilationUnit> handleToSource = sourceMapCaptor.getValue();

        // Verify the extracted class name exists
        assertTrue(nameToHandle.containsKey("TestEntity"), "The map should contain 'TestEntity'");
        assertEquals(1, nameToHandle.get("TestEntity").size());

        // Verify the handles and compilation units match
        ReferenceTypeHandle handle = nameToHandle.get("TestEntity").stream().findFirst().get();
        assertTrue(handleToSource.containsKey(handle), "Source map should contain the handle for the parsed class");
        
        CompilationUnit cu = handleToSource.get(handle);
        assertEquals("org.agentelf.test", cu.getPackageDeclaration().get().getNameAsString());
    }
}
