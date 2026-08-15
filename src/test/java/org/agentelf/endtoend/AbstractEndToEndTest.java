package org.agentelf.endtoend;

import com.github.mustachejava.DefaultMustacheFactory;
import org.agentelf.file.FileOutput;
import org.agentelf.llm.CallLLMList;
import org.agentelf.mustache.ApplyTemplate;
import org.agentelf.taskandaction.action.LoadContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.convention.TestBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(EndToEndTestConfig.class)
public abstract class AbstractEndToEndTest {

    @Autowired
    protected ApplicationContext applicationContext;

    @TestBean
    protected ApplyTemplate applyTemplate;

    @MockitoBean
    protected CallLLMList callLLMList;

    @MockitoBean
    protected LoadContext loadContext;

    @MockitoBean
    protected FileOutput fileOutput;

    @BeforeEach
    void setUp() throws Exception {
        when(loadContext.loadContext(anyString()))
                .thenAnswer(invocation -> invocation.getArgument(0));
    }

    public static ApplyTemplate applyTemplate() {
        return new ApplyTemplate(new DefaultMustacheFactory("org/agentelf/initialresource/template"));
    }

}
