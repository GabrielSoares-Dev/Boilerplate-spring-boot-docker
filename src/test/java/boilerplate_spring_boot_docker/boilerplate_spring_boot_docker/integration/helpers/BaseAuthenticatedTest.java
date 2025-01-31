package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.integration.helpers;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.services.AuthServiceInterface;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.models.User;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.repositories.user.UserJpaRepository;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BaseAuthenticatedTest {
  @Autowired private UserJpaRepository userJpaRepository;

  @Autowired private AuthServiceInterface authService;

  @Autowired protected MockMvc request;

  protected String tokenFormatted;

  @BeforeEach
  public void setUp() throws IllegalArgumentException, UnsupportedEncodingException {
    this.userJpaRepository.deleteAll();

    User model = new User();
    model.setName("test-name");
    model.setEmail("testuser@example.com");
    model.setPhoneNumber("1313131311");
    model.setPassword("{noop}test");
    this.userJpaRepository.save(model);

    String token = this.authService.generateToken("testuser@example.com");

    this.tokenFormatted = "Bearer " + token;
  }
}
