package ru.morrigan.spring.boot.demo.service;

import ru.morrigan.spring.boot.demo.model.Role;
import java.util.Set;

public interface RoleService {
  Set<Role> getAllRoles();
    void save(Role role);

  Role getRoleByName(String role);
}
