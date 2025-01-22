package spring_boot_base.spring_boot_base.application.dtos.repositories.task;

import java.time.LocalDateTime;
import spring_boot_base.spring_boot_base.domain.enums.TaskStatus;

public class FindTaskByIdRepositoryOutputDto {
  public Integer id;
  public String title;
  public String description;
  public TaskStatus status;
  public LocalDateTime createdAt;

  public FindTaskByIdRepositoryOutputDto(
      Integer id, String title, String description, TaskStatus status, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.createdAt = createdAt;
  }
}
