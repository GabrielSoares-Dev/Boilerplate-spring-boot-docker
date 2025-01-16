package spring_boot_to_do_list.spring_boot_to_do_list.unit.application.useCases.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.UpdateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.update.UpdateTaskUseCaseInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.services.LoggerServiceInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.UpdateTaskUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;

public class UpdateTaskUseCaseTest {
    @Mock
    private LoggerServiceInterface loggerServiceInterface;

    @Mock
    private TaskRepositoryInterface taskRepository;

    @InjectMocks
    private UpdateTaskUseCase useCase;

    private UpdateTaskUseCaseInputDto defaultInput = new UpdateTaskUseCaseInputDto(
            1,
            "test-title",
            "test-description",
            TaskStatus.COMPLETED);

    private UpdateTaskRepositoryInputDto expectedUpdateRepositoryInput = new UpdateTaskRepositoryInputDto(
            1,
            "test-title",
            "test-description",
            TaskStatus.COMPLETED);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdated() throws BusinessException {
        FindTaskByIdRepositoryOutputDto mockFindByIdOutput = new FindTaskByIdRepositoryOutputDto(
                1,
                "test-title",
                "test-description",
                TaskStatus.COMPLETED,
                LocalDateTime.now());
        when(taskRepository.findById(1)).thenReturn(Optional.of(mockFindByIdOutput));

        useCase.run(this.defaultInput);

        verify(taskRepository, times(1)).findById(1);
        ArgumentCaptor<UpdateTaskRepositoryInputDto> captor = ArgumentCaptor
                .forClass(UpdateTaskRepositoryInputDto.class);
        verify(taskRepository).update(captor.capture());

        UpdateTaskRepositoryInputDto capturedArgument = captor.getValue();
        assertEquals(defaultInput.id, capturedArgument.id);
        assertEquals(defaultInput.title, capturedArgument.title);
        assertEquals(defaultInput.description, capturedArgument.description);
        assertEquals(defaultInput.status, capturedArgument.status);

    }

    @Test
    public void testNotUpdatedIfTaskNotFound() throws BusinessException {
        when(taskRepository.findById(1)).thenReturn(Optional.empty());
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            useCase.run(this.defaultInput);
        });

        assertEquals("Task not found", exception.getMessage());

        verify(taskRepository, times(1)).findById(1);
        verify(taskRepository, times(0)).update(this.expectedUpdateRepositoryInput);
    }
}
