package com.example.telusko.JobAppNew.controller;

import com.example.telusko.JobAppNew.model.User;
import com.example.telusko.JobAppNew.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
