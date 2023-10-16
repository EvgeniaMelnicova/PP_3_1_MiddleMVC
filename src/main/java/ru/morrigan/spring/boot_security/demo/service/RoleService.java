package ru.morrigan.spring.boot_security.demo.service;

import ru.morrigan.spring.boot_security.demo.model.Role;
import java.util.Set;

public interface RoleService {
  Set<Role> getAllRoles();
    void save(Role role);
}
