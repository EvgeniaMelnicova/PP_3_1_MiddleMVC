package ru.morrigan.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.morrigan.spring.boot_security.demo.model.User;
import ru.morrigan.spring.boot_security.demo.service.RoleService;
import ru.morrigan.spring.boot_security.demo.service.UserService;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public String getAdminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getAllRoles());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "show";
    }

    // Времменный код

    // до сюда

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }
    // Времменный код

    // до сюда
    @PostMapping
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "roles") String[] roles){
        user.setRoles(roleService.getAllRoles());
        userService.save(user);
        return "redirect:/admin/users";
    }
    // Времменный код

    // до сюда
    @GetMapping("/users/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id){
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }
    // Времменный код

    // до сюда
    @PatchMapping("/users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                             @RequestParam(value = "roles") String[] roles){
        user.setRoles(roleService.getAllRoles());
        userService.updateUser(user);
        return "redirect:/admin/users";
    }
    // Времменный код

    // до сюда
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
    // Времменный код

    // до сюда
}
