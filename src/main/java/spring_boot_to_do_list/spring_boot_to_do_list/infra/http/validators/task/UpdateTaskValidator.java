package spring_boot_to_do_list.spring_boot_to_do_list.infra.http.validators.task;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;

public class UpdateTaskValidator {
  @NotEmpty(message = "title is required")
  @Size(max = 100, message = "title must be less than 100 characters")
  public String title;

  @NotEmpty(message = "description is required")
  @Size(max = 255, message = "description must be less than 255 characters")
  public String description;

  public TaskStatus status;
}
