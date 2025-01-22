package spring_boot_base.spring_boot_base.application.dtos.repositories.task;

import spring_boot_base.spring_boot_base.domain.enums.TaskStatus;

public class CreateTaskRepositoryInputDto {
  public String title;
  public String description;
  public TaskStatus status;

  public CreateTaskRepositoryInputDto(String title, String description, TaskStatus status) {
    this.title = title;
    this.description = description;
    this.status = status;
  }
}
