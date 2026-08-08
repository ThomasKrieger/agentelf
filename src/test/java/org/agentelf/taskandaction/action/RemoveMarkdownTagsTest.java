package org.agentelf.taskandaction.action;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveMarkdownTagsTest {

    @Test
    void shouldRemoveMarkdown() {
        String withMarkdown = """
```java
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
```
""";

        String withoutMarkdown = """

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

""";

        var result = new RemoveMarkdownTags().removeMarkdownTags(withMarkdown);
        assertEquals(withoutMarkdown , result);
    }

}
