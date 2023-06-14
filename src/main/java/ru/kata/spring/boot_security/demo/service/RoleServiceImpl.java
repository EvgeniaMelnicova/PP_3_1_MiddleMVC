package ru.kata.spring.boot_security.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;

  @Override
  public Set<Role> getAllRoles() {
    return StreamSupport
        .stream(roleRepository.findAll().spliterator(), false)
        .collect(Collectors.toSet());
  }
}
