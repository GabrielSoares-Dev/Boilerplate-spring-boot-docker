package spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task;

import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;

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
