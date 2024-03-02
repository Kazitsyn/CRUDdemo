package com.example.CRUDdemo.controllers;
import com.example.CRUDdemo.model.User;
import com.example.CRUDdemo.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Log
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        log.info("user-create" + user);
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
        log.info("user-delete");
        return "redirect:/users";
    }

    @GetMapping("user-update/{id}")
    public String updateUserForm(User user){

        return "user-update";
    }
    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.updateById(user);
        log.info("user-update" + user);
        return "redirect:/users";
    }
}
