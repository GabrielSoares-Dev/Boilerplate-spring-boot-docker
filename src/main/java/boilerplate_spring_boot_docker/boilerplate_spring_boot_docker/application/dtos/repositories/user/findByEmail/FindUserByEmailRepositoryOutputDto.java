package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.dtos.repositories.user.findByEmail;

public class FindUserByEmailRepositoryOutputDto {
  public Integer id;
  public String name;
  public String email;

  public FindUserByEmailRepositoryOutputDto(Integer id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }
}
