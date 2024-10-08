package com.Gestion.Gestapp.controller;

import com.Gestion.Gestapp.model.MyUser;
import com.Gestion.Gestapp.repository.MyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping
public class RegistrationController {

    private MyUserRepository myUserRepository;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public MyUser registerUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }

    @GetMapping("/register")
    public List<MyUser> vuUser() {
        return myUserRepository.findAll();
    }

    @GetMapping("/user")
    public String userPage(){
        return "I am a UserPage😁";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "I am a AdminPage😁";
    }

}
