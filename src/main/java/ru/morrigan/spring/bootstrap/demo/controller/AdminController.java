package ru.morrigan.spring.bootstrap.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.morrigan.spring.bootstrap.demo.model.User;
import ru.morrigan.spring.bootstrap.demo.service.RoleServiceImpl;
import ru.morrigan.spring.bootstrap.demo.service.UserServiceImpl;

import java.util.Optional;


@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/users")
    public String getAdminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "users";
    }

    @GetMapping("/users/{id}") // Проверить на работоспособность.
    public String showUser(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user.get());
        return "show";
    }

    @GetMapping("/users/new")
    public String getFormForCreate(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @GetMapping("/users/{id}/edit") // Пока не трогаем.
    public String getFormForUpdate(Model model, @PathVariable("id") Long id){
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PatchMapping("/users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                             @RequestParam(value = "roles") String[] roles){
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
