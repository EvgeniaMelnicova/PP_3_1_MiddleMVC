package ru.kata.spring.boot_security.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class StartupInitializer {
    private final RoleService rs;
    private final UserService us;
    private final UserRepository ur;
    private final BCryptPasswordEncoder bcpe;

    @PostConstruct
    public void initRoles(){
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        rs.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        rs.save(roleAdmin);
    // Этот работает

        Set<Role> roleListUser = new HashSet<>();
        User user = new User();
        user.setFirstName("User");
        user.setLastName("Usov");
        user.setMobilePhone("8(800)542-39-63");
        user.setEmail("User@yandex.com");
        user.setUsername(user.getFirstName());
        user.setPassword(bcpe.encode("user"));

        roleListUser.add(roleUser);
        user.setRoles(roleListUser);
        us.save(user);

        Set<Role> roleListAdmin = new HashSet<>();
        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Adminov");
        admin.setMobilePhone("8(800)542-39-63");
        admin.setEmail("Admin@yandex.com");
        admin.setUsername(admin.getFirstName());
        admin.setPassword(bcpe.encode("admin"));

        roleListAdmin.add(roleAdmin);
        admin.setRoles(roleListAdmin);
        us.save(admin);
}
}

