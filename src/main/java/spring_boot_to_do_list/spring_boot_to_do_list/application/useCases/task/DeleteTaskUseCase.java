package spring_boot_to_do_list.spring_boot_to_do_list.application.useCases.task;

import org.springframework.beans.factory.annotation.Autowired;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;

public class DeleteTaskUseCase {
    @Autowired
    private TaskRepositoryInterface taskRepository;

    public void run(Integer id) {
        this.taskRepository.delete(id);
    }
}
