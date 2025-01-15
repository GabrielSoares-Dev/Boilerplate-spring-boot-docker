package spring_boot_to_do_list.spring_boot_to_do_list.infra.http.validators.task;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CreateTaskValidator {
    @NotEmpty(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    public String title;

    @NotEmpty(message = "Description is required")
    @Size(max = 255, message = "Description must be less than 255 characters")
    public String description;
}
