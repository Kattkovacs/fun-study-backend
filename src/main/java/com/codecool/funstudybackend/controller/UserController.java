package com.codecool.funstudybackend.controller;


import com.codecool.funstudybackend.entity.User;
import com.codecool.funstudybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public User registration(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> login(@RequestBody User user) {
        User userByEmailAndPassword = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(userByEmailAndPassword != null);
    }
}
