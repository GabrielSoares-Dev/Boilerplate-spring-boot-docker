package spring_boot_to_do_list.spring_boot_to_do_list.application.repositories;

import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.CreateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindAllTasksRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.UpdateTaskRepositoryInputDto;
import java.util.List;
import java.util.Optional;

public interface TaskRepositoryInterface {
    void create(CreateTaskRepositoryInputDto input);

    List<FindAllTasksRepositoryOutputDto> findAll();

    Optional<FindTaskByIdRepositoryOutputDto> findById(Integer id);

    void update(UpdateTaskRepositoryInputDto input);

    void delete(Integer id);
}
