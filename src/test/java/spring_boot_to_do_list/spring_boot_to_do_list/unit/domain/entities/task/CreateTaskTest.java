package spring_boot_to_do_list.spring_boot_to_do_list.unit.domain.entities.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.entities.Task;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;

public class CreateTaskTest {

    private final String defaultTitle = "test title";
    private final String defaultDescription = "test description";
    private final LocalDateTime defaultCreationDate = LocalDateTime.now();
    private final TaskStatus defaultStatus = TaskStatus.PENDING;

    @Test
    public void testCreateTask() {
        assertDoesNotThrow(() -> {
            Task task = new Task(defaultTitle, defaultDescription, defaultCreationDate, defaultStatus);
            task.create();
        });
    }

    @Test
    public void testNotCreateTaskIfTitleIsEmpty() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task task = new Task("", defaultDescription, defaultCreationDate, defaultStatus);
            task.create();
        });
        assertEquals("Invalid title", exception.getMessage());
    }

    @Test
    public void testNotCreateTaskIfTitleIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task task = new Task(null, defaultDescription, defaultCreationDate, defaultStatus);
            task.create();
        });
        assertEquals("Invalid title", exception.getMessage());
    }

    @Test
    public void testNotCreateTaskIfDescriptionIsEmpty() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task task = new Task(defaultTitle, "", defaultCreationDate, defaultStatus);
            task.create();
        });
        assertEquals("Invalid description", exception.getMessage());
    }

    @Test
    public void testNotCreateTaskIfDescriptionIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task task = new Task(defaultTitle, null, defaultCreationDate, defaultStatus);
            task.create();
        });
        assertEquals("Invalid description", exception.getMessage());
    }

    @Test
    public void testNotCreateTaskIfCreationDateIsAfterNow() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task task = new Task(defaultTitle, defaultDescription, LocalDateTime.now().plusDays(1), defaultStatus);
            task.create();
        });
        assertEquals("Invalid creation date", exception.getMessage());
    }

    @Test
    public void testNotCreateTaskIfStatusIsNotPending() {
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            Task task = new Task(defaultTitle, defaultDescription, defaultCreationDate, TaskStatus.COMPLETED);
            task.create();
        });
        assertEquals("Invalid status", exception.getMessage());
    }
}