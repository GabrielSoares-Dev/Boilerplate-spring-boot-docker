package spring_boot_to_do_list.spring_boot_to_do_list.unit.application.useCases.task;

import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.CreateTaskUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.create.CreateTaskUseCaseInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreateTaskUseCaseTest {
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

        // verify(taskRepository, times(1));

    }
}
