package org.agentelf.endtoend;

import org.agentelf.file.FileOutput;
import org.agentelf.llm.CallLLMList;
import org.agentelf.taskandaction.action.LoadContext;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(EndToEndTestConfig.class)
public abstract class AbstractEndToEndTest {

    @Autowired
    protected ApplicationContext applicationContext;

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

}
