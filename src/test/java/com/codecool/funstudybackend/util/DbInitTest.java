package com.codecool.funstudybackend.util;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class DbInitTest {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    DbInit dbInit;

    @Test
    public void testDbInit() {
        int cardCount = 10;
        dbInit.initDb(cardCount);
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(cardCount * 2);
    }

    @Test
    public void testDbInitCountCardWithImage() {
        int cardCount = 10;
        dbInit.initDb(cardCount);
        List<Card> cardList = cardRepository.getAllCardWithPicture();
        assertThat(cardList).hasSize(cardCount);
    }

    @Test
    public void testDbInitCountCardWithOutImage() {
        int cardCount = 10;
        dbInit.initDb(cardCount);
        List<Card> cardList = cardRepository.getAllCardWithOutPicture();
        assertThat(cardList).hasSize(cardCount);
    }

}