package spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.useCases.task.findById.FindTaskByIdUseCaseOutputDto;

@Service
public class FindTaskByIdUseCase {
    @Autowired
    private TaskRepositoryInterface taskRepository;

    public FindTaskByIdUseCaseOutputDto run(Integer id) throws BusinessException {
        Optional<FindTaskByIdRepositoryOutputDto> findTask = this.taskRepository.findById(id);

        boolean notFound = !findTask.isPresent();
        if (notFound) {
            throw new BusinessException("Task not found");
        }

        FindTaskByIdRepositoryOutputDto taskData = findTask.get();

        return new FindTaskByIdUseCaseOutputDto(
                taskData.id,
                taskData.title,
                taskData.description,
                taskData.creationDate,
                taskData.status);
    }
}
