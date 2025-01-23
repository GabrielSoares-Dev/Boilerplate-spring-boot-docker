package spring_boot_base.spring_boot_base.application.useCases.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_base.spring_boot_base.application.dtos.useCases.hello.HelloUseCaseInputDto;
import spring_boot_base.spring_boot_base.application.repositories.HelloRepositoryInterface;
import spring_boot_base.spring_boot_base.application.services.LoggerServiceInterface;

@Service
public class HelloUseCase {
  @Autowired private LoggerServiceInterface loggerService;

  @Autowired private HelloRepositoryInterface helloRepository;

  private String logContext = "HelloUseCase";

  public String run(HelloUseCaseInputDto input) {
    this.loggerService.debug(String.format("Start %s with input: ", this.logContext), input);

    this.helloRepository.create(input.name);

    String output = String.format("Hello %s!", input.name);

    this.loggerService.debug("output: ", output);
    this.loggerService.debug(String.format("Finish %s ", this.logContext));
    return output;
  }
}
