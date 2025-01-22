package spring_boot_base.spring_boot_base.application.services;

public interface LoggerServiceInterface {
  void info(String message);

  void debug(String message);

  void error(String message);

  void warning(String message);

  void info(String message, Object object);

  void debug(String message, Object object);

  void error(String message, Object object);

  void warn(String message, Object object);
}
