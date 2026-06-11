package dev.agentelf.task;

import dev.agentelf.api.RunVariables;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TaskBeanActionTest {

    @Test
    void execute_shouldInvokeActionAndWriteReturnVariable() {
        // arrange
        ActionGuineaPig bean = new ActionGuineaPig();
        TaskBean executor = new TaskBean(bean);

        RunVariables vars = new RunVariables();
        vars.setPrompt("hello");

        // pre-condition
        assertNull(vars.getLlmResponse());

        // act
        executor.execute(vars);

        // assert
        assertEquals("hello test", vars.getLlmResponse());
    }

}
