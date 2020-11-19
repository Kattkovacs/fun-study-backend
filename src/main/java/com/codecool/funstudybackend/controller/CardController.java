package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.view.UnknownCard;
import com.codecool.funstudybackend.repository.CardRepository;
import com.codecool.funstudybackend.repository.UserRepository;
import com.codecool.funstudybackend.service.RemoteURLReader;
import com.codecool.funstudybackend.entity.User;
import com.codecool.funstudybackend.service.APIService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/card-with-picture")
    public Card createCardWithPictureContent() throws IOException {
        Random random = new Random();
        List<Card> cardsWithPicture = cardRepository.getAllCardWithPicture();
        return cardsWithPicture.get(random.nextInt(cardsWithPicture.size()));
    }

    @GetMapping("/card-without-picture")
    public Card createCardWithOutPictureContent() throws IOException {
        Random random = new Random();
        List<Card> cardsWithoutPicture = cardRepository.getAllCardWithOutPicture();
        return cardsWithoutPicture.get(random.nextInt(cardsWithoutPicture.size()));
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
    public Card saveCard(@RequestBody UnknownCard unknownCard ){
        return cardRepository.findCardByWord(unknownCard.getWord());
    }
}