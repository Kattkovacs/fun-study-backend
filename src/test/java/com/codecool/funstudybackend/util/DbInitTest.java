package com.codecool.funstudybackend.util;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
class DbInitTest {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    DbInit dbInit;

    @Test
    public void testDbInit() {
        cardRepository.deleteAll();
        int cardCount = 10;
        dbInit.initDb();
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(cardCount * 2);

    }

    @Test
    public void testDbInitCountCardWithImage() {
        cardRepository.deleteAll();
        int cardCount = 10;
        dbInit.initDb();
        List<Card> cardList = cardRepository.getAllCardWithPicture();
        assertThat(cardList).hasSize(cardCount);
    }

    @Test
    public void testDbInitCountCardWithOutImage() {
        cardRepository.deleteAll();
        int cardCount = 10;
        dbInit.initDb();
        List<Card> cardList = cardRepository.getAllCardWithOutPicture();
        assertThat(cardList).hasSize(cardCount);
    }

}