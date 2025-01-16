package spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.findById.FindTaskByIdUseCaseOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.services.LoggerServiceInterface;

@Service
public class FindTaskByIdUseCase {
  @Autowired private LoggerServiceInterface loggerService;

  @Autowired private TaskRepositoryInterface taskRepository;

  private String logContext = "FindTaskByIdUseCase";

  public FindTaskByIdUseCaseOutputDto run(Integer id) throws BusinessException {
    this.loggerService.debug(String.format("Start %s with input: ", this.logContext), id);
    Optional<FindTaskByIdRepositoryOutputDto> findTask = this.taskRepository.findById(id);

    boolean notFound = !findTask.isPresent();
    this.loggerService.debug(String.format("not found task: %b ", notFound));

    if (notFound) {
      throw new BusinessException("Task not found");
    }

    FindTaskByIdRepositoryOutputDto taskData = findTask.get();

    FindTaskByIdUseCaseOutputDto output =
        new FindTaskByIdUseCaseOutputDto(
            taskData.id, taskData.title, taskData.description, taskData.status, taskData.createdAt);
    this.loggerService.debug("output: ", output);
    this.loggerService.debug(String.format("Finish %s ", this.logContext));

    return output;
  }
}
