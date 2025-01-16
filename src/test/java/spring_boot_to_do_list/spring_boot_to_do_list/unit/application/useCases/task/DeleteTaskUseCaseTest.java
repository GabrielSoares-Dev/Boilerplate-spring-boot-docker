package spring_boot_to_do_list.spring_boot_to_do_list.unit.application.useCases.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.services.LoggerServiceInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.DeleteTaskUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;

public class DeleteTaskUseCaseTest {
  @Mock private LoggerServiceInterface loggerServiceInterface;

  @Mock private TaskRepositoryInterface taskRepository;

  @InjectMocks private DeleteTaskUseCase useCase;

  private Integer defaultId = 1;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testDeleted() {
    FindTaskByIdRepositoryOutputDto mockFindByIdOutput =
        new FindTaskByIdRepositoryOutputDto(
            defaultId, "Task 1", "Description 1", TaskStatus.IN_PROGRESS, LocalDateTime.now());
    when(taskRepository.findById(defaultId)).thenReturn(Optional.of(mockFindByIdOutput));

    assertDoesNotThrow(
        () -> {
          useCase.run(this.defaultId);
        });

    verify(taskRepository, times(1)).findById(defaultId);
    verify(taskRepository, times(1)).delete(defaultId);
  }

  @Test
  public void testNotDeletedIfTaskNotFound() throws BusinessException {
    when(taskRepository.findById(defaultId)).thenReturn(Optional.empty());
    BusinessException exception =
        assertThrows(
            BusinessException.class,
            () -> {
              useCase.run(this.defaultId);
            });

    assertEquals("Task not found", exception.getMessage());

    verify(taskRepository, times(1)).findById(defaultId);
    verify(taskRepository, times(0)).delete(defaultId);
  }
}
