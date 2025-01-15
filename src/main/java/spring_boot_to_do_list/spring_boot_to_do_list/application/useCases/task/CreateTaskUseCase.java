package spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task;

import spring_boot_to_do_list.spring_boot_to_do_list.domain.entities.Task;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.create.CreateTaskUseCaseInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.CreateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskUseCase {
    @Autowired
    private TaskRepositoryInterface taskRepository;

    private void validateTask(CreateTaskUseCaseInputDto input) throws BusinessException {
        Task entity = new Task(input.title, input.description, LocalDateTime.now(), TaskStatus.PENDING);

        entity.create();
    }

    private void createIntoDatabase(CreateTaskUseCaseInputDto input) {
        this.taskRepository.create(
                new CreateTaskRepositoryInputDto(
                        input.title,
                        input.description,
                        LocalDateTime.now(),
                        TaskStatus.PENDING));
    }

    public void run(CreateTaskUseCaseInputDto input) throws BusinessException {
        this.validateTask(input);

        this.createIntoDatabase(input);
    }
}
