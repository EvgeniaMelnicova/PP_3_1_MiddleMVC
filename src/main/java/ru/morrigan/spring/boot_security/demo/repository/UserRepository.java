package ru.morrigan.spring.boot_security.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.morrigan.spring.boot_security.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  User findByEmail(String email);
}