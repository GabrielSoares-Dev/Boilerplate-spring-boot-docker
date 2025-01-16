package spring_boot_to_do_list.spring_boot_to_do_list.infra.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.CreateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindAllTasksRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.UpdateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;

@Repository
public class TaskRepository implements TaskRepositoryInterface {
    @Override
    public void create(CreateTaskRepositoryInputDto input) {
    }

    @Override
    public List<FindAllTasksRepositoryOutputDto> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Optional<FindTaskByIdRepositoryOutputDto> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(UpdateTaskRepositoryInputDto input) {
    }

    @Override
    public void delete(Integer id) {
    }

}
