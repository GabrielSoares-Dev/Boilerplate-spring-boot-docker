package spring_boot_base.spring_boot_base.application.useCases.task;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_base.spring_boot_base.application.dtos.repositories.task.FindAllTasksRepositoryOutputDto;
import spring_boot_base.spring_boot_base.application.dtos.useCases.task.list.ListTasksUseCaseOutputDto;
import spring_boot_base.spring_boot_base.application.repositories.TaskRepositoryInterface;
import spring_boot_base.spring_boot_base.application.services.LoggerServiceInterface;

@Service
public class ListTasksUseCase {
  @Autowired private LoggerServiceInterface loggerService;

  @Autowired private TaskRepositoryInterface taskRepository;

  private String logContext = "ListTasksUseCase";

  public List<ListTasksUseCaseOutputDto> run() {
    this.loggerService.debug(String.format("Start %s ", this.logContext));
    List<FindAllTasksRepositoryOutputDto> tasks = this.taskRepository.findAll();

    List<ListTasksUseCaseOutputDto> output =
        tasks.stream()
            .map(
                task ->
                    new ListTasksUseCaseOutputDto(
                        task.id, task.title, task.description, task.status, task.createdAt))
            .collect(Collectors.toList());
    this.loggerService.debug("output: ", output);

    this.loggerService.debug(String.format("Finish %s ", this.logContext));
    return output;
  }
}
