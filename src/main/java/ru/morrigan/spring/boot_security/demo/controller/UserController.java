package ru.morrigan.spring.boot_security.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.morrigan.spring.boot_security.demo.service.UserService;

@AllArgsConstructor
@RequestMapping("/")
@Controller
public class UserController {

    @GetMapping("/user")
    public String userInfo(Model model) {
        final UserDetails user = (UserDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "showUser";
    }
// Перенос из Логин контроллера
    @GetMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
