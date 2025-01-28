package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.services;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.dtos.repositories.user.findByEmail.FindUserByEmailRepositoryOutputDto;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.repositories.UserRepositoryInterface;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.services.AuthServiceInterface;
import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.services.EncryptionServiceInterface;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServiceInterface {
  @Autowired private UserRepositoryInterface userRepository;

  @Autowired private EncryptionServiceInterface encryptionService;

  @Value("${spring.jwt.secret}")
  private String SECRET_KEY;

  @Value("${spring.jwt.expiration}")
  private long EXPIRATION_TIME;

  public String generateToken(String email)
      throws IllegalArgumentException, UnsupportedEncodingException {
    Algorithm algorithm = Algorithm.HMAC256(this.SECRET_KEY);
    return JWT.create()
        .withSubject(email)
        .withExpiresAt(new Date(System.currentTimeMillis() + this.EXPIRATION_TIME))
        .sign(algorithm);
  }

  public boolean validateToken(String token) {
    return true;
  }

  public boolean validateCredentials(String email, String password) {
    Optional<FindUserByEmailRepositoryOutputDto> searchUser =
        this.userRepository.findByEmail(email);

    boolean userNotFound = searchUser.isEmpty();

    if (userNotFound) {
      return false;
    }

    String encryptedPassword = searchUser.get().password;

    boolean matchesPassword = this.encryptionService.matches(password, encryptedPassword);

    boolean isValid = matchesPassword;

    return isValid;
  }
}
