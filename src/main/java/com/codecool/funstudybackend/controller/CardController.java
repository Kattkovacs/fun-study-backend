package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.model.RemoteURLReader;
import com.codecool.funstudybackend.model.User;
import com.codecool.funstudybackend.service.APIService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

@RestController
public class CardController {
    @Autowired
    RemoteURLReader remoteURLReader;

    @Autowired
    APIService apiService;

    @CrossOrigin //(origins = "http://localhost:3000")
    @GetMapping("/card")
    public ObjectNode createCardContent() throws IOException {
        ObjectNode result = apiService.findCardContentFromResult(apiService.askForCardJson(RandomWordGenerator.getRandomWord()));
        return result;
    }

    @CrossOrigin
    @PostMapping(
            value = "/registration", consumes = "application/json", produces = "application/json")
    public String registration(@RequestBody User user){
        System.out.println(user.getEmail());
        return user.getEmail();
    }
}