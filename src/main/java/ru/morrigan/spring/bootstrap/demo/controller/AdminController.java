package ru.morrigan.spring.bootstrap.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.morrigan.spring.bootstrap.demo.model.User;
import ru.morrigan.spring.bootstrap.demo.service.RoleService;
import ru.morrigan.spring.bootstrap.demo.service.UserService;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles") String[] roles){
        user.setRoles(roleService.getAllRoles());
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id){
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                             @RequestParam(value = "roles") String[] roles){
        user.setRoles(roleService.getAllRoles());
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
