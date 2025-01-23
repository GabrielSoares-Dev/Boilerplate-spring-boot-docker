package spring_boot_base.spring_boot_base.infra.repositories.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring_boot_base.spring_boot_base.application.repositories.HelloRepositoryInterface;
import spring_boot_base.spring_boot_base.infra.models.Hello;

@Repository
public class HelloRepository implements HelloRepositoryInterface {
  @Autowired private HelloJpaRepository helloJpaRepository;

  @Override
  public void create(String name) {
    Hello input = new Hello();
    input.setName(name);
    helloJpaRepository.save(input);
  }
}
