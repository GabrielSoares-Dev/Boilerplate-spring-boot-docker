package spring_boot_to_do_list.spring_boot_to_do_list.unit.application.useCases.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindAllTasksRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.list.ListTasksUseCaseOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.ListTasksUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;

public class ListTasksUseCaseTest {
    @Mock
    private TaskRepositoryInterface taskRepository;

    @InjectMocks
    private ListTasksUseCase useCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListTasks() {
        LocalDateTime currentDate = LocalDateTime.now();
        FindAllTasksRepositoryOutputDto task1 = new FindAllTasksRepositoryOutputDto(
                1,
                "Task 1",
                "Description 1",
                TaskStatus.PENDING,
                currentDate);
        FindAllTasksRepositoryOutputDto task2 = new FindAllTasksRepositoryOutputDto(
                2,
                "Task 2",
                "Description 2",
                TaskStatus.COMPLETED,
                currentDate);

        when(taskRepository.findAll()).thenReturn(Arrays.asList(task1, task2));

        List<ListTasksUseCaseOutputDto> output = useCase.run();

        ListTasksUseCaseOutputDto outputTask1 = output.get(0);
        assertEquals(1, outputTask1.id);
        assertEquals("Task 1", outputTask1.title);
        assertEquals("Description 1", outputTask1.description);
        assertEquals(currentDate, outputTask1.createdAt);
        assertEquals(TaskStatus.PENDING, outputTask1.status);

        ListTasksUseCaseOutputDto outputTask2 = output.get(1);
        assertEquals(2, outputTask2.id);
        assertEquals("Task 2", outputTask2.title);
        assertEquals("Description 2", outputTask2.description);
        assertEquals(currentDate, outputTask2.createdAt);
        assertEquals(TaskStatus.COMPLETED, outputTask2.status);
    }
}
