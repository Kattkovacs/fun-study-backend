package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.entity.ApplicationUser;
import com.codecool.funstudybackend.repository.CardRepository;
import com.codecool.funstudybackend.repository.UserRepository;
import com.codecool.funstudybackend.security.JwtTokenServices;
import com.codecool.funstudybackend.view.UnknownCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@CrossOrigin
@RequestMapping("/")
@RestController
public class CardController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    JwtTokenServices jwtTokenServices;

    private List<Card> usedCardList = new ArrayList<>();

    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    @GetMapping("card")
    public Card createCardContent() throws IOException {
        List<Card> cardList = cardRepository.findAll();
        if (cardList.size() == usedCardList.size()) {
            usedCardList = new ArrayList<>();
        }
        Random random = new Random();
        Card selectedCard = cardList.get(random.nextInt(cardList.size()));
        while (usedCardList.contains(selectedCard)) {
            selectedCard = cardList.get(random.nextInt(cardList.size()));
        }
        usedCardList.add(selectedCard);
        return selectedCard;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    @GetMapping("clear-memory-game")
    public void clearMemoryGame() {
        usedCardList.clear();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    @GetMapping("card-with-picture")
    public Card createCardWithPictureContent() throws IOException {
        List<Card> cardList = cardRepository.getAllCardWithPicture();
        Random random = new Random();
        return cardList.get(random.nextInt(cardList.size()));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    @GetMapping("card-without-picture")
    public Card createCardWithOutPictureContent() throws IOException {
        Random random = new Random();
        List<Card> cardsWithoutPicture = cardRepository.getAllCardWithOutPicture();
        return cardsWithoutPicture.get(random.nextInt(cardsWithoutPicture.size()));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'PLAYER')")
    @PostMapping(value = "savecard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> saveCard(@RequestBody UnknownCard unknownCard, @RequestHeader("Authorization") String token) {
        Card card = cardRepository.findCardByWord(unknownCard.getWord());

        String email = jwtTokenServices.getEmailFromToken(token);
        System.out.println(email);
        Optional<ApplicationUser> user = userRepository.findApplicationUserByEmail(email);
        if(user.isPresent()) {
            ApplicationUser validUser = user.get();
            validUser.addUnknownCard(card);
            card.addUser(validUser);
            userRepository.save(validUser);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

}