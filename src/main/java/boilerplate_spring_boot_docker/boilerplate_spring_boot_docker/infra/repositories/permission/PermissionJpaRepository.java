package boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.repositories.permission;

import boilerplate_spring_boot_docker.boilerplate_spring_boot_docker.infra.models.Permission;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionJpaRepository extends JpaRepository<Permission, Integer> {
  Optional<Permission> findByName(String name);
}
