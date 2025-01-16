package spring_boot_to_do_list.spring_boot_to_do_list.application.repositories;

import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.CreateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import java.util.Optional;

public interface TaskRepositoryInterface {
    void create(CreateTaskRepositoryInputDto input);

    Optional<FindTaskByIdRepositoryOutputDto> findById(Integer id);

    void delete(Integer id);
}
