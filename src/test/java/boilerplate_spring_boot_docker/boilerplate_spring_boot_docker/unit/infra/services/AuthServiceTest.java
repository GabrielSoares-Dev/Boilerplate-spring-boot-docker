package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.unit.infra.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.dtos.repositories.user.findByEmail.FindUserByEmailRepositoryOutputDto;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.repositories.UserRepositoryInterface;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.services.EncryptionServiceInterface;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.services.AuthService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthServiceTest {
  @Mock private EncryptionServiceInterface encryptionService;

  @Mock private UserRepositoryInterface userRepository;

  @InjectMocks private AuthService authService;

  private String secretKey = "test";

  private long expirationTime = 10000;

  private void setField(Object target, String fieldName, Object value)
      throws NoSuchFieldException, IllegalAccessException {
    Field field = target.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);
    field.set(target, value);
  }

  @BeforeEach
  public void setUp() throws NoSuchFieldException, IllegalAccessException {
    MockitoAnnotations.openMocks(this);
    setField(authService, "SECRET_KEY", secretKey);
    setField(authService, "EXPIRATION_TIME", expirationTime);
  }

  @Test
  public void testGenerateToken() throws UnsupportedEncodingException {
    String email = "test@example.com";
    String output = authService.generateToken(email);

    assertNotNull(output);

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    JWT.require(algorithm).build().verify(output);
  }

  @Test
  public void testValidateCredentialsSuccess() {
    String email = "john.doe@example.com";
    String password = "Password@123";
    String encryptedPassword = "$2a$10$DowJonesIndex";

    FindUserByEmailRepositoryOutputDto findUserByEmailRepositoryOutputMock =
        new FindUserByEmailRepositoryOutputDto(1, "John Doe", email, encryptedPassword);

    when(this.userRepository.findByEmail(email))
        .thenReturn(Optional.of(findUserByEmailRepositoryOutputMock));
    when(this.encryptionService.matches(password, encryptedPassword)).thenReturn(true);

    boolean output = authService.validateCredentials(email, password);

    assertTrue(output);
  }

  public void testValidateCredentialsNotMatchPassword() {
    String email = "test@example.com";
    String password = "WrongPassword";
    String encryptedPassword = "$2a$10$DowJonesIndex";

    FindUserByEmailRepositoryOutputDto findUserByEmailRepositoryOutputMock =
        new FindUserByEmailRepositoryOutputDto(1, "John Doe", email, encryptedPassword);

    when(userRepository.findByEmail(email))
        .thenReturn(Optional.of(findUserByEmailRepositoryOutputMock));
    when(encryptionService.matches(password, encryptedPassword)).thenReturn(false);

    boolean output = authService.validateCredentials(email, password);

    assertFalse(output);
  }

  @Test
  public void testValidateCredentialsUserNotFound() {
    String email = "test@example.com";
    String password = "Password@123";

    when(this.userRepository.findByEmail(email)).thenReturn(Optional.empty());

    boolean output = authService.validateCredentials(email, password);

    assertFalse(output);
  }
}
