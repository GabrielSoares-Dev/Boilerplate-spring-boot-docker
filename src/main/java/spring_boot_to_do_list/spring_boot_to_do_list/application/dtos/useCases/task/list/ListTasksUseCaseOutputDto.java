package spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.list;

import java.time.LocalDateTime;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;

public class ListTasksUseCaseOutputDto {
    public Integer id;
    public String title;
    public String description;
    public TaskStatus status;
    public LocalDateTime createdAt;

    public ListTasksUseCaseOutputDto(
            Integer id,
            String title,
            String description,
            TaskStatus status,
            LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }
}
