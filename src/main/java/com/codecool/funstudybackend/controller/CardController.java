package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.view.UnknownCard;
import com.codecool.funstudybackend.repository.CardRepository;
import com.codecool.funstudybackend.repository.UserRepository;
import com.codecool.funstudybackend.service.RemoteURLReader;
import com.codecool.funstudybackend.entity.User;
import com.codecool.funstudybackend.service.APIService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
@CrossOrigin
@RestController
public class CardController {
    @Autowired
    RemoteURLReader remoteURLReader;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    APIService apiService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/card")
    public Card createCardContent() throws IOException {
//        ObjectNode result = apiService.findCardContentFromResult(apiService.askForCardJson(RandomWordGenerator.getRandomWord()));
        Card result = Card.builder()
                .word("horse")
                .definition("big animal")
                .imageUrl(null)
                .build();
        return result;
    }

    @GetMapping("/card-with-picture")
    public Card createCardWithPictureContent() throws IOException {
        List<Card> cardList = cardRepository.getAllCardWithPicture();
        Random random = new Random();
        return cardList.get(random.nextInt(cardList.size()));
    }

    @GetMapping("/card-without-picture")
    public Card createCardWithOutPictureContent() throws IOException {
        List<Card> cardList = cardRepository.getAllCardWithOutPicture();
        Random random = new Random();
        return cardList.get(random.nextInt(cardList.size()));
    }

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

    @PostMapping(value = "/savecard", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> saveCard(@RequestBody HashMap<String, String> unknownCard ){
//        UnknownCard
//        cardRepository.findCardByWord(unknownCard.getWord());
        return unknownCard;
    }
}