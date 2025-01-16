package spring_boot_to_do_list.spring_boot_to_do_list.application.services;

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
