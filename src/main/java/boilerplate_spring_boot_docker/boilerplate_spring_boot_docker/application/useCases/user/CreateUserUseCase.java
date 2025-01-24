package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.useCases.user;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.dtos.useCases.user.create.CreateUserUseCaseInputDto;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.services.LoggerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
  @Autowired private LoggerServiceInterface loggerService;

  private String logContext = "CreateUserUseCase";

  public void run(CreateUserUseCaseInputDto input) {
    this.loggerService.debug(String.format("Start %s with input: ", this.logContext), input);

    this.loggerService.debug(String.format("Finish %s ", this.logContext));
  }
}
