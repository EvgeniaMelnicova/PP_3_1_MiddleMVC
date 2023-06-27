package ru.kata.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping("/")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }
}
