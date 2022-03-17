package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {


    @GetMapping
    public String userPage (@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("role", user.getRoles());

        return "user";
    }


}
