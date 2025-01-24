package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.unit.domain.entities.user;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.exceptions.BusinessException;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.domain.entities.User;
import org.junit.jupiter.api.Test;

public class CreateUserTest {

  @Test
  public void testValidToCreate() {
    assertDoesNotThrow(
        () -> {
          User user = new User("John Doe", "john.doe@example.com", "12345678901", "Password@123");
          user.create();
        });
  }

  @Test
  public void testInvalidName() {
    BusinessException exception =
        assertThrows(
            BusinessException.class,
            () -> {
              User user = new User("", "john.doe@example.com", "12345678901", "Password@123");
              user.create();
            });
    assertEquals("Invalid name", exception.getMessage());
  }

  @Test
  public void testInvalidEmail() {
    BusinessException exception =
        assertThrows(
            BusinessException.class,
            () -> {
              User user = new User("John Doe", "invalid-email", "12345678901", "Password@123");
              user.create();
            });
    assertEquals("Invalid email", exception.getMessage());
  }

  @Test
  public void testInvalidPhoneNumber() {
    BusinessException exception =
        assertThrows(
            BusinessException.class,
            () -> {
              User user = new User("John Doe", "john.doe@example.com", "12345", "Password@123");
              user.create();
            });
    assertEquals("Invalid phone number", exception.getMessage());
  }

  @Test
  public void testInvalidPassword() {
    BusinessException exception =
        assertThrows(
            BusinessException.class,
            () -> {
              User user = new User("John Doe", "john.doe@example.com", "12345678901", "password");
              user.create();
            });
    assertEquals("Invalid password", exception.getMessage());
  }
}
