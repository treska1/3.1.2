package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private final RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping
    public String startPage(){
        return "admin";
    }
    @GetMapping("/allusers")
    public String getAllUser(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("allUsers", list);
        return "allusers";
    }
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin/allusers";
    }

    @GetMapping("user-delete/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin/allusers";
    }


    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user",userService.getUserById(id));
        return "user-update";
    }

    @PostMapping("/user-update/{id}")
    public String userUpdate(@ModelAttribute("user") User user,@RequestParam("roleSelect") long []roles) {
        Set<Role> roleSet = new HashSet<>();
        for (long role: roles) {
            roleSet.add(roleService.getRoleById(role));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin/allusers";

    }
}
