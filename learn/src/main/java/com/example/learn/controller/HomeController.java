package com.example.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping("/")
    public String redirectToSwagger(){
        return "redirect:/swagger-ui.html";
    }
}
