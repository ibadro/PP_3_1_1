package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String printUsers(ModelMap model, @RequestParam() Optional<Integer> count) {
        model.addAttribute("users", userService.getUserList(count.orElse(100)));
        model.addAttribute("user_model", new User());
        return "index";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(ModelMap model, @PathVariable long id) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getUserList(100));
        model.addAttribute("user_model", new User());
        return "index";
    }

    @PostMapping(value = "/add")
    public String addUser(ModelMap model, @ModelAttribute User user) {
        userService.addUser(user);
        model.addAttribute("users", userService.getUserList(100));
        model.addAttribute("user_model", new User());
        return "index";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUserPage(ModelMap model, @PathVariable long id) {
        model.addAttribute("user_for_edit", userService.getUserById(id));
        return "index";
    }

    @PatchMapping(value = "/edit")
    public String editUser(ModelMap model, @ModelAttribute User user) {
        userService.updateUser(user);
        model.addAttribute("users", userService.getUserList(100));
        model.addAttribute("user_model", new User());
        return "index";
    }

    @GetMapping(value = "/test")
    public String testMethod() {
        return "index";
    }

}
