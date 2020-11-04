package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.model.RemoteURLReader;
import com.codecool.funstudybackend.service.APIService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}