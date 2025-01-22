package spring_boot_base.spring_boot_base.application.dtos.repositories.task;

import spring_boot_base.spring_boot_base.domain.enums.TaskStatus;

public class UpdateTaskRepositoryInputDto {
  public Integer id;
  public String title;
  public String description;
  public TaskStatus status;

  public UpdateTaskRepositoryInputDto(
      Integer id, String title, String description, TaskStatus status) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
  }
}
