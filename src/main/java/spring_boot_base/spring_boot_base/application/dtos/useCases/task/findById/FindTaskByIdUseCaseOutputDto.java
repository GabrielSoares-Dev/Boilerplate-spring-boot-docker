package spring_boot_base.spring_boot_base.application.dtos.useCases.task.findById;

import java.time.LocalDateTime;
import spring_boot_base.spring_boot_base.domain.enums.TaskStatus;

public class FindTaskByIdUseCaseOutputDto {
  public Integer id;
  public String title;
  public String description;
  public TaskStatus status;
  public LocalDateTime createdAt;

  public FindTaskByIdUseCaseOutputDto(
      Integer id, String title, String description, TaskStatus status, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.createdAt = createdAt;
  }
}
