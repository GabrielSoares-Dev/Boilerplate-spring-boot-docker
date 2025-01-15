package spring_boot_to_do_list.spring_boot_to_do_list.infra.http.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping
    public String getAllTasks() {
        return "List of tasks";
    }
}

