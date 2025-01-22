package spring_boot_base.spring_boot_base.application.useCases.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_base.spring_boot_base.application.exceptions.BusinessException;
import spring_boot_base.spring_boot_base.application.repositories.TaskRepositoryInterface;
import spring_boot_base.spring_boot_base.application.services.LoggerServiceInterface;

@Service
public class DeleteTaskUseCase {
  @Autowired private LoggerServiceInterface loggerService;

  @Autowired private TaskRepositoryInterface taskRepository;

  private String logContext = "DeleteTaskUseCase";

  public void run(Integer id) throws BusinessException {
    this.loggerService.debug(String.format("Start %s with input: ", this.logContext), id);

    boolean notFound = !this.taskRepository.findById(id).isPresent();
    this.loggerService.debug(String.format("not found task: %b ", notFound));

    if (notFound) {
      throw new BusinessException("Task not found");
    }

    this.taskRepository.delete(id);
    this.loggerService.debug("Task deleted");

    this.loggerService.debug(String.format("Finish %s ", this.logContext));
  }
}
