package com.lax.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/home")
    public String home(){
        return "This is Home page";
    }

    @GetMapping("/home-1")
    public String home_1(){
        return "This is Home_1 page";
    }

    @GetMapping("/home_login")
    public String home_login(){
        return "This is Home_login page";
    }
}
