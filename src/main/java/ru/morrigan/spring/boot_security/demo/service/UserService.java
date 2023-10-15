package ru.morrigan.spring.boot_security.demo.service;

import ru.morrigan.spring.boot_security.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
  void createUser(User user);
  void deleteUser(long id);
  void updateUser(User user);
  List<User> getUsers();
  Optional<User> getUserById(Long id);
    void save(User user);
}
