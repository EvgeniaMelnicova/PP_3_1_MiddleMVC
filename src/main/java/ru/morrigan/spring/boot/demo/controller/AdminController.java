package ru.morrigan.spring.boot.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.morrigan.spring.boot.demo.model.User;
import ru.morrigan.spring.boot.demo.service.RoleServiceImpl;
import ru.morrigan.spring.boot.demo.service.UserServiceImpl;

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

    @GetMapping("/users/{id}")
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

    @GetMapping("/users/{id}/edit")
    public String getFormForUpdate(Model model, @PathVariable("id") Long id){
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user.get());
        model.addAttribute("roles", roleService.getAllRoles());
        System.out.println("ТЕСТ ПРЕДОСТАВЛЕНИЯ ФОРМЫ РЕДАКТИРОВАНИЯ");
        return "edit";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PatchMapping("/users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id ){
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
