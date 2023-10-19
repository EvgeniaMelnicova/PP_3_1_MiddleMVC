package ru.morrigan.spring.bootstrap.demo.service;

import ru.morrigan.spring.bootstrap.demo.model.Role;
import java.util.Set;

public interface RoleService {
  Set<Role> getAllRoles();
    void save(Role role);

  Role getRoleByName(String role);
}
