package spring_boot_base.spring_boot_base.application.dtos.useCases.task.create;

public class CreateTaskUseCaseInputDto {
  public String title;
  public String description;

  public CreateTaskUseCaseInputDto(String title, String description) {
    this.title = title;
    this.description = description;
  }
}
