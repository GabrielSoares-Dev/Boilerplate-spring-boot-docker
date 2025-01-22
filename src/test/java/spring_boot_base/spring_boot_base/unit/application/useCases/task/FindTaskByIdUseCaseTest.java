package spring_boot_base.spring_boot_base.unit.application.useCases.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring_boot_base.spring_boot_base.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import spring_boot_base.spring_boot_base.application.dtos.useCases.task.findById.FindTaskByIdUseCaseOutputDto;
import spring_boot_base.spring_boot_base.application.exceptions.BusinessException;
import spring_boot_base.spring_boot_base.application.repositories.TaskRepositoryInterface;
import spring_boot_base.spring_boot_base.application.services.LoggerServiceInterface;
import spring_boot_base.spring_boot_base.application.useCases.task.FindTaskByIdUseCase;
import spring_boot_base.spring_boot_base.domain.enums.TaskStatus;

public class FindTaskByIdUseCaseTest {
  @Mock private LoggerServiceInterface loggerServiceInterface;

  @Mock private TaskRepositoryInterface taskRepository;

  @InjectMocks private FindTaskByIdUseCase useCase;

  private Integer defaultId = 1;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testFound() throws BusinessException {
    LocalDateTime currentDate = LocalDateTime.now();

    FindTaskByIdRepositoryOutputDto mockFindByIdOutput =
        new FindTaskByIdRepositoryOutputDto(
            defaultId, "Task 1", "Description 1", TaskStatus.IN_PROGRESS, currentDate);
    when(taskRepository.findById(defaultId)).thenReturn(Optional.of(mockFindByIdOutput));

    FindTaskByIdUseCaseOutputDto output = useCase.run(this.defaultId);
    FindTaskByIdUseCaseOutputDto expectedOutput =
        new FindTaskByIdUseCaseOutputDto(
            defaultId, "Task 1", "Description 1", TaskStatus.IN_PROGRESS, currentDate);

    assertEquals(expectedOutput.id, output.id);
    assertEquals(expectedOutput.title, output.title);
    assertEquals(expectedOutput.description, output.description);
    assertEquals(expectedOutput.createdAt, output.createdAt);
    assertEquals(expectedOutput.status, output.status);

    verify(taskRepository, times(1)).findById(defaultId);
  }

  @Test
  public void testTaskNotFound() throws BusinessException {
    when(taskRepository.findById(defaultId)).thenReturn(Optional.empty());
    BusinessException exception =
        assertThrows(
            BusinessException.class,
            () -> {
              useCase.run(this.defaultId);
            });

    assertEquals("Task not found", exception.getMessage());

    verify(taskRepository, times(1)).findById(defaultId);
  }
}
