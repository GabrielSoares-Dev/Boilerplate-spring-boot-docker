package spring_boot_to_do_list.spring_boot_to_do_list.infra.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.CreateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindAllTasksRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.FindTaskByIdRepositoryOutputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.dtos.repositories.task.UpdateTaskRepositoryInputDto;
import spring_boot_to_do_list.spring_boot_to_do_list.application.repositories.TaskRepositoryInterface;
import spring_boot_to_do_list.spring_boot_to_do_list.infra.models.Task;

@Repository
public class TaskRepository implements TaskRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(CreateTaskRepositoryInputDto input) {
        Task task = new Task();
        task.setTitle(input.title);
        task.setDescription(input.description);
        task.setStatus(input.status);
        entityManager.persist(task);
    }

    @Override
    public List<FindAllTasksRepositoryOutputDto> findAll() {
        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        return tasks.stream().map(task -> new FindAllTasksRepositoryOutputDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FindTaskByIdRepositoryOutputDto> findById(Integer id) {
        Task task = entityManager.find(Task.class, id);

        if (task == null) {
            return Optional.empty();
        }

        FindTaskByIdRepositoryOutputDto output = new FindTaskByIdRepositoryOutputDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt());

        return Optional.of(output);
    }

    @Override
    @Transactional
    public void update(UpdateTaskRepositoryInputDto input) {
        Task task = entityManager.find(Task.class, input.id);

        if (task != null) {
            task.setTitle(input.title);
            task.setDescription(input.description);
            task.setStatus(input.status);
            entityManager.merge(task);
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Task task = entityManager.find(Task.class, id);

        if (task != null) {
            entityManager.remove(task);
        }
    }

}
