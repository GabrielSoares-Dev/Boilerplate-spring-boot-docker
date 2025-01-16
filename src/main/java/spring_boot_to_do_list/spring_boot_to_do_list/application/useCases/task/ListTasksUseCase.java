package spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindAllTasksRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.list.ListTasksUseCaseOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;

@Service
public class ListTasksUseCase {
    @Autowired
    private TaskRepositoryInterface taskRepository;

    public List<ListTasksUseCaseOutputDto> run() {
        List<FindAllTasksRepositoryOutputDto> tasks = this.taskRepository.findAll();

        return tasks.stream()
                .map(task -> new ListTasksUseCaseOutputDto(
                        task.id,
                        task.title,
                        task.description,
                        task.status,
                        task.createdAt))
                .collect(Collectors.toList());
    }
}
