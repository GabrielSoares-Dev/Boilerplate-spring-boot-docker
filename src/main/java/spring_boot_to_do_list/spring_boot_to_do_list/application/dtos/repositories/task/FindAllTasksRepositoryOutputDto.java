package spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task;

import java.time.LocalDateTime;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;

public class FindAllTasksRepositoryOutputDto {
    public Integer id;
    public String title;
    public String description;
    public LocalDateTime creationDate;
    public TaskStatus status;

    public FindAllTasksRepositoryOutputDto(
            Integer id,
            String title,
            String description,
            LocalDateTime creationDate,
            TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.status = status;
    }
}
