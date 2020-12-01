package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.entity.ApplicationUser;
import com.codecool.funstudybackend.repository.CardRepository;
import com.codecool.funstudybackend.repository.UserRepository;
import com.codecool.funstudybackend.view.UnknownCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    private List<Card> usedCardList = new ArrayList<>();

    @PreAuthorize("hasAnyRole('ROLE_PLAYER','ROLE_ADMIN')")
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

    @PreAuthorize("hasAnyRole('ROLE_PLAYER','ROLE_ADMIN')")
    @GetMapping("clear-memory-game")
    public void clearMemoryGame() {
        usedCardList.clear();
    }

    @PreAuthorize("hasAnyRole('ROLE_PLAYER','ROLE_ADMIN')")
    @GetMapping("card-with-picture")
    public Card createCardWithPictureContent() throws IOException {
        List<Card> cardList = cardRepository.getAllCardWithPicture();
        Random random = new Random();
        return cardList.get(random.nextInt(cardList.size()));
    }

    @PreAuthorize("hasAnyRole('ROLE_PLAYER','ROLE_ADMIN')")
    @GetMapping("card-without-picture")
    public Card createCardWithOutPictureContent() throws IOException {
        Random random = new Random();
        List<Card> cardsWithoutPicture = cardRepository.getAllCardWithOutPicture();
        return cardsWithoutPicture.get(random.nextInt(cardsWithoutPicture.size()));
    }

    @PreAuthorize("hasAnyRole('ROLE_PLAYER','ROLE_ADMIN')")
    @PostMapping(value = "savecard", consumes = "application/json", produces = "application/json")
    public Card saveCard(@RequestBody UnknownCard unknownCard) {
        Card card = cardRepository.findCardByWord(unknownCard.getWord());
        ApplicationUser user = userRepository.findUserByEmail(unknownCard.getEmail());
        user.addUnknownCard(card);
        userRepository.save(user);
        card.addUser(user);
        cardRepository.save(card);
        return card;
    }

}