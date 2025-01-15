package spring_boot_to_do_list.spring_boot_to_do_list.infra.repositories;

import org.springframework.stereotype.Repository;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.CreateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;

@Repository
public class TaskRepository implements TaskRepositoryInterface {

    @Override
    public void create(CreateTaskRepositoryInputDto input) {
    }

}
