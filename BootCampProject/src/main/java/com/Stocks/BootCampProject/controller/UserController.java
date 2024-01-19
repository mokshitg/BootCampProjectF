package com.Stocks.BootCampProject.controller;

import com.Stocks.BootCampProject.entity.User;
import com.Stocks.BootCampProject.error.UserNotFoundException;
import com.Stocks.BootCampProject.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public User saveUser(@Valid @RequestBody User user){
        return userService.saveUser(user);

    }

    @GetMapping("/{id}")
    public User fetchUserById(@PathVariable("id") int user_id) throws UserNotFoundException {

        return userService.fetchUserById(user_id);

    }
}
