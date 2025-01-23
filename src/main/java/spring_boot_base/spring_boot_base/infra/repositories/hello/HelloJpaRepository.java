package spring_boot_base.spring_boot_base.infra.repositories.hello;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot_base.spring_boot_base.infra.models.Hello;

public interface HelloJpaRepository extends JpaRepository<Hello, Integer> {}
