package org.agentelf.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionWrapperBeanTest {


        @Test
        void shouldSetProperty() {
            TaskGuineaPig taskGuineaPig = new TaskGuineaPig();

            ActionWrapperBean wrapper = new ActionWrapperBean(taskGuineaPig);
            wrapper.addProperty("description", "My Task");
            assertEquals("My Task", taskGuineaPig.getDescription());
        }

        @Test
        void shouldThrowExceptionForUnknownProperty() {
            TaskGuineaPig taskGuineaPig = new TaskGuineaPig();

            ActionWrapperBean wrapper = new ActionWrapperBean(taskGuineaPig);

            RuntimeException ex = assertThrows(
                    RuntimeException.class,
                    () -> wrapper.addProperty("unknownProperty", "value"));

            assertTrue(ex.getMessage().contains("unknownProperty"));
        }
    }