package com.codecool.funstudybackend.controller;


import com.codecool.funstudybackend.entity.ApplicationUser;
import com.codecool.funstudybackend.view.UserContainer;
import com.codecool.funstudybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> registration(@RequestBody UserContainer user) {
        ApplicationUser applicationUser = ApplicationUser.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .date(user.getDate())
                .build();

        userRepository.save(applicationUser);
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> login(@RequestBody UserContainer user) {
        ApplicationUser userByEmail = userRepository.findUserByEmail(user.getEmail());
        boolean loggedIn = passwordEncoder.matches(user.getPassword(), userByEmail.getPassword());
        return ResponseEntity.ok(loggedIn);
    }
}
