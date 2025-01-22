package spring_boot_base.spring_boot_base.application.useCases.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_base.spring_boot_base.application.dtos.repositories.task.CreateTaskRepositoryInputDto;
import spring_boot_base.spring_boot_base.application.dtos.useCases.task.create.CreateTaskUseCaseInputDto;
import spring_boot_base.spring_boot_base.application.exceptions.BusinessException;
import spring_boot_base.spring_boot_base.application.repositories.TaskRepositoryInterface;
import spring_boot_base.spring_boot_base.application.services.LoggerServiceInterface;
import spring_boot_base.spring_boot_base.domain.entities.Task;
import spring_boot_base.spring_boot_base.domain.enums.TaskStatus;

@Service
public class CreateTaskUseCase {
  @Autowired private LoggerServiceInterface loggerService;

  @Autowired private TaskRepositoryInterface taskRepository;

  private String logContext = "CreateTaskUseCase";

  private void validateTask(CreateTaskUseCaseInputDto input) throws BusinessException {
    Task entity = new Task(input.title, input.description, TaskStatus.PENDING);

    entity.create();
  }

  private void createIntoDatabase(CreateTaskUseCaseInputDto input) {
    this.taskRepository.create(
        new CreateTaskRepositoryInputDto(input.title, input.description, TaskStatus.PENDING));
  }

  public void run(CreateTaskUseCaseInputDto input) throws BusinessException {
    this.loggerService.debug(String.format("Start %s with input: ", this.logContext), input);
    this.validateTask(input);

    this.createIntoDatabase(input);

    this.loggerService.debug(String.format("Finish %s ", this.logContext));
  }
}
