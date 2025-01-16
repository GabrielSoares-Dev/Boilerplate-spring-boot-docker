package spring_boot_to_do_list.spring_boot_to_do_list.unit.application.useCases.task;

import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.CreateTaskUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.CreateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.create.CreateTaskUseCaseInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.services.LoggerServiceInterface;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateTaskUseCaseTest {
    @Mock
    private LoggerServiceInterface loggerServiceInterface;

    @Mock
    private TaskRepositoryInterface taskRepository;

    @InjectMocks
    private CreateTaskUseCase useCase;

    private final CreateTaskUseCaseInputDto defaultInput = new CreateTaskUseCaseInputDto(
            "test title",
            "test description");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreate() throws BusinessException {
        assertDoesNotThrow(() -> {
            useCase.run(this.defaultInput);
        });

        ArgumentCaptor<CreateTaskRepositoryInputDto> captor = ArgumentCaptor
                .forClass(CreateTaskRepositoryInputDto.class);
        verify(taskRepository, times(1)).create(captor.capture());

        CreateTaskRepositoryInputDto capturedArgument = captor.getValue();
        assertEquals(defaultInput.title, capturedArgument.title);
        assertEquals(defaultInput.description, capturedArgument.description);
        assertEquals(TaskStatus.PENDING, capturedArgument.status);

    }
}
