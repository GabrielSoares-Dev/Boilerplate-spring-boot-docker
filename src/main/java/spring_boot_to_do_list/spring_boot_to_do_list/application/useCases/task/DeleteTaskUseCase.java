package spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;

@Service
public class DeleteTaskUseCase {
    @Autowired
    private TaskRepositoryInterface taskRepository;

    public void run(Integer id) throws BusinessException {
        boolean notFound = !this.taskRepository.findById(id).isPresent();
        if (notFound) {
            throw new BusinessException("Task not found");
        }
        
        this.taskRepository.delete(id);
    }
}
