package spring_boot_base.spring_boot_base.application.repositories;

import java.util.List;
import java.util.Optional;
import spring_boot_base.spring_boot_base.application.dtos.repositories.task.CreateTaskRepositoryInputDto;
import spring_boot_base.spring_boot_base.application.dtos.repositories.task.FindAllTasksRepositoryOutputDto;
import spring_boot_base.spring_boot_base.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import spring_boot_base.spring_boot_base.application.dtos.repositories.task.UpdateTaskRepositoryInputDto;

public interface TaskRepositoryInterface {
  void create(CreateTaskRepositoryInputDto input);

  List<FindAllTasksRepositoryOutputDto> findAll();

  Optional<FindTaskByIdRepositoryOutputDto> findById(Integer id);

  void update(UpdateTaskRepositoryInputDto input);

  void delete(Integer id);
}
