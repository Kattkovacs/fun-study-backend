package com.codecool.funstudybackend.util;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.repository.CardRepository;
import com.codecool.funstudybackend.service.APIService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbInit {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private APIService apiService;

    private int cardCount = 10;
    private String[] pictureWords = new String[]{"lamp", "cat", "dog", "horse", "fish", "catfish", "hammer", "flag", "beer", "hamster"
    ,"rabbit","mouse","rat","owl","crab","shark","jellyfish","turtle","car","bus"
            ,"gun","shield","helmet","blue","red"
    };

    public void initDb() {
        for (int i = 0; i < cardCount; i++) {
            ObjectNode cardData1 = apiService.findCardContentFromResult(apiService.askForCardJson(RandomWordGenerator.getRandomWord()));
            Card tempCard1 = Card.builder()
                    .word(cardData1.get("word").toString())
                    .definition(cardData1.get("definition").toString())
                    .image_url(null)
                    .build();
            cardRepository.save(tempCard1);
        }
        for (int i = 0; i < cardCount; i++) {
            ObjectNode cardData2 = apiService.findCardContentFromResult(apiService.askForCardJsonWithImg(pictureWords[i]));
            Card tempCard2 = Card.builder()
                    .word(cardData2.get("word").toString())
                    .definition(cardData2.get("definition").toString())
                    .image_url(cardData2.get("image_url").toString())
                    .build();
            cardRepository.save(tempCard2);
        }
    }

}
