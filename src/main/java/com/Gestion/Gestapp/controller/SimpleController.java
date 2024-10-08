package com.Gestion.Gestapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SimpleController {

    @GetMapping
    public String publicPAge(){
        return "Hello Word 🌍";
    }

    @GetMapping("private")
    public String PrivatePage(){
        return "I am a private PAge 😒";
    }


}
