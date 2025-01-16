package spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.UpdateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.update.UpdateTaskUseCaseInputDto;

@Service
public class UpdateTaskUseCase {
    @Autowired
    private TaskRepositoryInterface taskRepository;

    private void updateIntoDatabase(UpdateTaskUseCaseInputDto input) {
        this.taskRepository
                .update(new UpdateTaskRepositoryInputDto(input.id, input.title, input.description, input.status));
    }

    public void run(UpdateTaskUseCaseInputDto input) throws BusinessException {
        boolean notFound = !this.taskRepository.findById(input.id).isPresent();
        if (notFound) {
            throw new BusinessException("Task not found");
        }

        this.updateIntoDatabase(input);

    }
}
