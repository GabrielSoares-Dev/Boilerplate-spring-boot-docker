package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.unit.infra.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.services.EncryptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionServiceTest {

  private EncryptionService encryptionService;

  @BeforeEach
  public void setUp() {
    encryptionService = new EncryptionService();
  }

  @Test
  public void testEncrypt() {
    String rawPassword = "Password@123";
    String encryptedPassword = encryptionService.encrypt(rawPassword);

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    assertTrue(passwordEncoder.matches(rawPassword, encryptedPassword));
  }
}
