package com.lax.security.controller;

import com.lax.security.model.User;
import com.lax.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //all users
    @GetMapping("/")
    public List<User> getAllUsers(){
        return this.userService.getAllUser();
    }

    //Return single user
    @GetMapping("/{userName}")
    public User getSingleUser(@PathVariable("userName") String userName){
        return this.userService.getSingleUser(userName);
    }

    //Add new user
    @PostMapping("/")
    public User add(@RequestBody User user){
        return this.userService.addUser(user);
    }
}
