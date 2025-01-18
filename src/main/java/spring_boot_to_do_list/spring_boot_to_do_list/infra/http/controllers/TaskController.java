package spring_boot_to_do_list.spring_boot_to_do_list.infra.http.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.create.CreateTaskUseCaseInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.findById.FindTaskByIdUseCaseOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.list.ListTasksUseCaseOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.update.UpdateTaskUseCaseInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.services.LoggerServiceInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.CreateTaskUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.DeleteTaskUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.FindTaskByIdUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.ListTasksUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.UpdateTaskUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.infra.helpers.BaseResponse;
import spring_boot_to_do_list.spring_boot_to_do_list.infra.http.validators.task.CreateTaskValidator;
import spring_boot_to_do_list.spring_boot_to_do_list.infra.http.validators.task.UpdateTaskValidator;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
  @Autowired private LoggerServiceInterface loggerService;

  @Autowired private CreateTaskUseCase createTaskUseCase;

  @Autowired private ListTasksUseCase listTasksUseCase;

  @Autowired private FindTaskByIdUseCase findTaskByIdUseCase;

  @Autowired private UpdateTaskUseCase updateTaskUseCase;

  @Autowired private DeleteTaskUseCase deleteTaskUseCase;

  private String logContext = "TaskController";

  @PostMapping
  public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateTaskValidator input) {
    this.loggerService.debug(String.format("Start %s create with input: ", this.logContext), input);
    try {
      this.createTaskUseCase.run(new CreateTaskUseCaseInputDto(input.title, input.description));
      return BaseResponse.success("Task created successfully", HttpStatus.CREATED);
    } catch (BusinessException exception) {
      String errorMessage = exception.getMessage();
      this.loggerService.error("Error:", errorMessage);
      return BaseResponse.error(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  public ResponseEntity<Map<String, Object>> list() {
    this.loggerService.debug(String.format("Start %s list", this.logContext));

    List<ListTasksUseCaseOutputDto> output = this.listTasksUseCase.run();
    this.loggerService.debug("output: ", output);

    return BaseResponse.successWithContent("Tasks retrieved successfully", HttpStatus.OK, output);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
    this.loggerService.debug(String.format("Start %s findById with input: ", this.logContext), id);
    try {
      FindTaskByIdUseCaseOutputDto output = this.findTaskByIdUseCase.run(id);
      this.loggerService.debug("output: ", output);

      return BaseResponse.successWithContent("Task found successfully", HttpStatus.OK, output);
    } catch (BusinessException exception) {
      String errorMessage = exception.getMessage();
      HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

      boolean isNotFoundError = errorMessage == "Task not found";

      if (isNotFoundError) {
        httpStatus = HttpStatus.BAD_REQUEST;
      }
      this.loggerService.error("Error:", errorMessage);
      return BaseResponse.error(errorMessage, httpStatus);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Map<String, Object>> update(
      @PathVariable Integer id, @Valid @RequestBody UpdateTaskValidator input) {
    this.loggerService.debug(String.format("Start %s update with input: ", this.logContext), input);
    try {
      this.updateTaskUseCase.run(
          new UpdateTaskUseCaseInputDto(id, input.title, input.description, input.status));
      return BaseResponse.success("Task updated successfully", HttpStatus.OK);
    } catch (BusinessException exception) {
      String errorMessage = exception.getMessage();
      HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

      boolean isNotFoundError = "Task not found" == errorMessage;

      if (isNotFoundError) {
        httpStatus = HttpStatus.BAD_REQUEST;
      }
      this.loggerService.error("Error:", errorMessage);
      return BaseResponse.error(errorMessage, httpStatus);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
    this.loggerService.debug(String.format("Start %s update with input: ", this.logContext), id);
    try {
      this.deleteTaskUseCase.run(id);
      return BaseResponse.success("Task deleted successfully", HttpStatus.OK);
    } catch (BusinessException exception) {
      String errorMessage = exception.getMessage();
      HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

      boolean isNotFoundError = errorMessage == "Task not found";

      if (isNotFoundError) {
        httpStatus = HttpStatus.BAD_REQUEST;
      }
      this.loggerService.error("Error:", errorMessage);
      return BaseResponse.error(errorMessage, httpStatus);
    }
  }

  // @GetMapping("/hello")
  // public String hello() {
  //   return "hello";
  // }
}
