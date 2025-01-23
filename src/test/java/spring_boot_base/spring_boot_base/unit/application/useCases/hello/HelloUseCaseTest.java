package spring_boot_base.spring_boot_base.unit.application.useCases.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring_boot_base.spring_boot_base.application.dtos.useCases.hello.HelloUseCaseInputDto;
import spring_boot_base.spring_boot_base.application.services.LoggerServiceInterface;
import spring_boot_base.spring_boot_base.application.useCases.hello.HelloUseCase;

public class HelloUseCaseTest {
  @Mock private LoggerServiceInterface loggerServiceInterface;

  @InjectMocks private HelloUseCase useCase;

  private final HelloUseCaseInputDto defaultInput = new HelloUseCaseInputDto("test name");

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testHello() {
    String output = useCase.run(this.defaultInput);

    String expectedOutput = "Hello test name!";

    assertEquals(expectedOutput, output);
  }
}
