package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.services;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.application.services.LoggerServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerService implements LoggerServiceInterface {
  private final Logger logger = LoggerFactory.getLogger(LoggerService.class);
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void info(String message) {
    logger.info(message);
  }

  @Override
  public void debug(String message) {
    logger.debug(message);
  }

  @Override
  public void error(String message) {
    logger.error(message);
  }

  @Override
  public void warning(String message) {
    logger.warn(message);
  }

  @Override
  public void info(String message, Object object) {
    try {
      String jsonString = objectMapper.writeValueAsString(object);
      logger.info("{} - {}", message, jsonString);
    } catch (JsonProcessingException exception) {
      logger.error("Failed to convert object to JSON", exception);
    }
  }

  @Override
  public void debug(String message, Object object) {
    try {
      String jsonString = objectMapper.writeValueAsString(object);
      logger.debug("{} - {}", message, jsonString);
    } catch (JsonProcessingException exception) {
      logger.error("Failed to convert object to JSON", exception);
    }
  }

  @Override
  public void error(String message, Object object) {
    try {
      String jsonString = objectMapper.writeValueAsString(object);
      logger.error("{} - {}", message, jsonString);
    } catch (JsonProcessingException exception) {
      logger.error("Failed to convert object to JSON", exception);
    }
  }

  @Override
  public void warn(String message, Object object) {
    try {
      String jsonString = objectMapper.writeValueAsString(object);
      logger.warn("{} - {}", message, jsonString);
    } catch (JsonProcessingException exception) {
      logger.error("Failed to convert object to JSON", exception);
    }
  }
}
