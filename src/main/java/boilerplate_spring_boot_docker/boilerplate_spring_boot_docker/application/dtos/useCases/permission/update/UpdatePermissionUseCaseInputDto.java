package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.dtos.useCases.permission.update;

public class UpdatePermissionUseCaseInputDto {
  public Integer id;
  public String name;
  public String description;

  public UpdatePermissionUseCaseInputDto(Integer id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }
}
