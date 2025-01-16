package spring_boot_to_do_list.spring_boot_to_do_list.unit.domain.entities.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.entities.Task;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;

public class CreateTaskTest {
    private final String defaultTitle = "test title";
    private final String defaultDescription = "test description";
    private final TaskStatus defaultStatus = TaskStatus.PENDING;

    @Test
    public void testCreateTask() {
        assertDoesNotThrow(() -> {
            Task entity = new Task(defaultTitle, defaultDescription, defaultStatus);
            entity.create();
        });
    }

    @Test
    public void testNotCreateTaskIfTitleIsEmpty() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task entity = new Task("", defaultDescription, defaultStatus);
            entity.create();
        });
        assertEquals("Invalid title", exception.getMessage());
    }

    @Test
    public void testNotCreateTaskIfTitleIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task entity = new Task(null, defaultDescription, defaultStatus);
            entity.create();
        });
        assertEquals("Invalid title", exception.getMessage());
    }

    @Test
    public void testNotCreateTaskIfDescriptionIsEmpty() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task entity = new Task(defaultTitle, "", defaultStatus);
            entity.create();
        });
        assertEquals("Invalid description", exception.getMessage());
    }

    @Test
    public void testNotCreateTaskIfDescriptionIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task entity = new Task(defaultTitle, null, defaultStatus);
            entity.create();
        });
        assertEquals("Invalid description", exception.getMessage());
    }

    @Test
    public void testNotCreateTaskIfStatusIsNotPending() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task entity = new Task(defaultTitle, defaultDescription, TaskStatus.COMPLETED);
            entity.create();
        });
        assertEquals("Invalid status", exception.getMessage());
    }
}