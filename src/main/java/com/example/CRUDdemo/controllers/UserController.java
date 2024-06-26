package com.example.CRUDdemo.controllers;
import com.example.CRUDdemo.model.User;
import com.example.CRUDdemo.service.FileGateway;
import com.example.CRUDdemo.service.UserService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log
@RequiredArgsConstructor
public class UserController {

    private final Counter requestCounter = Metrics.counter("add_count");
    private final UserService userService;
    private final FileGateway fileGateway;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        fileGateway.writeToFile("GET\\users.txt", LocalDateTime.now().toString());
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
        requestCounter.increment();
        fileGateway.writeToFile("POST\\user-create.txt", user.toString() + LocalDateTime.now());
        return "redirect:/users";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteById(id);
        fileGateway.writeToFile("GET\\user-delete.txt", LocalDateTime.now().toString());
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
        fileGateway.writeToFile("POST\\user-update.txt", user.toString() + LocalDateTime.now());
        log.info("user-update" + user);
        return "redirect:/users";
    }
}
