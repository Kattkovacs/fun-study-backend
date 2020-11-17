package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.service.RemoteURLReader;
import com.codecool.funstudybackend.entity.User;
import com.codecool.funstudybackend.service.APIService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

@CrossOrigin
@RestController
public class CardController {
    @Autowired
    RemoteURLReader remoteURLReader;

    @Autowired
    APIService apiService;

    @Autowired
    User user;

    @GetMapping("/card")
    public ObjectNode createCardContent() throws IOException {
        ObjectNode result = apiService.findCardContentFromResult(apiService.askForCardJson(RandomWordGenerator.getRandomWord()));
        return result;
    }

    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public User registration(@RequestBody User user) {
        this.user = user;
        return user;
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> login(@RequestBody User user) {
        return ResponseEntity.ok(this.user.getEmail().equals(user.getEmail()) && this.user.getPassword().equals(user.getPassword()));
    }
}