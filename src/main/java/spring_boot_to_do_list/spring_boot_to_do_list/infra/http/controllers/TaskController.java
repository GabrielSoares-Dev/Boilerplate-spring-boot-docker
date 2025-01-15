package spring_boot_to_do_list.spring_boot_to_do_list.infra.http.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.create.CreateTaskUseCaseInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task.CreateTaskUseCase;
import spring_boot_to_do_list.spring_boot_to_do_list.infra.helpers.BaseResponse;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private CreateTaskUseCase createTaskUseCase;

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody CreateTaskUseCaseInputDto input) {
        try {
            this.createTaskUseCase.run(input);
            return BaseResponse.success("Task created successfully", HttpStatus.CREATED);
        } catch (BusinessException exception) {
            String errorMessage = exception.getMessage();
            return BaseResponse.error(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
