package com.codecool.funstudybackend.repository;

import com.codecool.funstudybackend.entity.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class CardRepositoryTest {

    @Autowired
    CardRepository cardRepository;

    @Test
    public void saveOneCard(){
        Card testCard = Card.builder().word("cat").definition("fluffy animal with 4 leg.").imageUrl(null).build();
        cardRepository.save(testCard);
        List<Card> cardList = cardRepository.findAll();
        assertThat(cardList).hasSize(1);
    }

    @Test
    public void findOnlyCardWithImage(){
        Card testCard1 = Card.builder().word("cat3").definition("fluffy animal with 4 leg.").imageUrl("asdf").build();
        Card testCard2 = Card.builder().word("cat4").definition("fluffy animal with 4 leg.").imageUrl("qwer").build();
        Card testCard3 = Card.builder().word("cat5").definition("fluffy animal with 4 leg.").imageUrl("íyxc").build();
        cardRepository.save(testCard1);
        cardRepository.save(testCard2);
        cardRepository.save(testCard3);
        List<Card> cardList = cardRepository.findAll();
        List<Card> cardListWithImg = cardRepository.getAllCardWithPicture();
        assertThat(cardList).hasSize(3);
        assertThat(cardListWithImg).hasSize(3);
    }

    @Test
    public void findOnlyCardWithOutImage(){
        Card testCard1 = Card.builder().word("cat3").definition("fluffy animal with 4 leg.").imageUrl(null).build();
        Card testCard2 = Card.builder().word("cat4").definition("fluffy animal with 4 leg.").imageUrl(null).build();
        Card testCard3 = Card.builder().word("cat5").definition("fluffy animal with 4 leg.").imageUrl(null).build();
        cardRepository.save(testCard1);
        cardRepository.save(testCard2);
        cardRepository.save(testCard3);
        List<Card> cardList = cardRepository.findAll();
        List<Card> cardListWithImg = cardRepository.getAllCardWithOutPicture();
        assertThat(cardList).hasSize(3);
        assertThat(cardListWithImg).hasSize(3);
    }

    @Test
    public void saveMultipleCard(){
        Card testCard1 = Card.builder().word("cat1").definition("fluffy animal with 4 leg.").imageUrl(null).build();
        Card testCard2 = Card.builder().word("cat2").definition("fluffy animal with 4 leg.").imageUrl(null).build();
        Card testCard3 = Card.builder().word("cat3").definition("fluffy animal with 4 leg.").imageUrl("asdf").build();
        Card testCard4 = Card.builder().word("cat4").definition("fluffy animal with 4 leg.").imageUrl("qwer").build();
        Card testCard5 = Card.builder().word("cat5").definition("fluffy animal with 4 leg.").imageUrl("íyxc").build();
        cardRepository.save(testCard1);
        cardRepository.save(testCard2);
        cardRepository.save(testCard3);
        cardRepository.save(testCard4);
        cardRepository.save(testCard5);
        List<Card> cardList = cardRepository.findAll();
        List<Card> cardListWithImg = cardRepository.getAllCardWithPicture();
        List<Card> cardListWithOutImg = cardRepository.getAllCardWithOutPicture();
        assertThat(cardList).hasSize(5);
        assertThat(cardListWithImg).hasSize(3);
        assertThat(cardListWithOutImg).hasSize(2);
    }



}