package spring_boot_to_do_list.spring_boot_to_do_list.application.repositories;

import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.CreateTaskRepositoryInputDto;

public interface TaskRepositoryInterface {
    void create(CreateTaskRepositoryInputDto input);
}
