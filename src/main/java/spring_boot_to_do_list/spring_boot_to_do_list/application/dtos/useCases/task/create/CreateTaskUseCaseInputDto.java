package spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.create;

public class CreateTaskUseCaseInputDto {
    public String title;
    public String description;

    public CreateTaskUseCaseInputDto(String title, String description) {
        this.title = title;
        this.description = description;
    }
}