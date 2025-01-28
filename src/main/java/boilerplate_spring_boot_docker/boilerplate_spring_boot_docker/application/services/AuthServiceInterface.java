package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.services;

import java.io.UnsupportedEncodingException;

public interface AuthServiceInterface {
  String generateToken(String email) throws IllegalArgumentException, UnsupportedEncodingException;

  boolean validateCredentials(String email, String password);
}
