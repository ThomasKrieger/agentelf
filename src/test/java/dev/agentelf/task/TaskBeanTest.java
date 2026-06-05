package dev.agentelf.task;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskBeanTest {


        @Test
        void shouldSetProperty() {
            TaskGuineaPig taskGuineaPig = new TaskGuineaPig();

            TaskBean wrapper = new TaskBean(taskGuineaPig);
            wrapper.addProperty("description", "My Task");
            assertEquals("My Task", taskGuineaPig.getDescription());
        }

        @Test
        void shouldAddTaskToExistingCollection() {
            TaskGuineaPig parent = new TaskGuineaPig();
            parent.setTasks(new java.util.ArrayList<>());

            TaskGuineaPig child = new TaskGuineaPig();

            TaskBean parentWrapper = new TaskBean(parent);
            TaskBean childWrapper = new TaskBean(child);

            parentWrapper.addTask(childWrapper);

            assertEquals(1, parent.getTasks().size());
            assertSame(child, ((TaskBean)parent.getTasks().get(0)).getBean());
        }

        @Test
        void shouldCreateActionsCollectionIfNull() {
            TaskGuineaPig parent = new TaskGuineaPig();
            TaskGuineaPig child = new TaskGuineaPig();

            TaskBean parentWrapper = new TaskBean(parent);
            TaskBean childWrapper = new TaskBean(child);

            parentWrapper.addTask(childWrapper);

            assertNotNull(parent.getTasks());
            assertEquals(1, parent.getTasks().size());
            assertSame(child,  ((TaskBean)parent.getTasks().get(0)).getBean());
        }

        @Test
        void shouldAddMultipleActions() {
            TaskGuineaPig parent = new TaskGuineaPig();

            TaskGuineaPig child1 = new TaskGuineaPig();
            child1.setDescription("child1");

            TaskGuineaPig child2 = new TaskGuineaPig();
            child2.setDescription("child2");

            TaskBean parentWrapper = new TaskBean(parent);

            parentWrapper.addTask(new TaskBean(child1));
            parentWrapper.addTask(new TaskBean(child2));

            List<Task> actions = parent.getTasks();

            assertEquals(2, actions.size());
            assertEquals("child1",((TaskGuineaPig)((TaskBean) actions.get(0)).getBean()).getDescription());
            assertEquals("child2", ((TaskGuineaPig)((TaskBean) actions.get(1)).getBean()).getDescription());
        }

        @Test
        void shouldThrowExceptionForUnknownProperty() {
            TaskGuineaPig taskGuineaPig = new TaskGuineaPig();

            TaskBean wrapper = new TaskBean(taskGuineaPig);

            RuntimeException ex = assertThrows(
                    RuntimeException.class,
                    () -> wrapper.addProperty("unknownProperty", "value"));

            assertTrue(ex.getMessage().contains("unknownProperty"));
        }
    }