package com.codecool.funstudybackend.repository;

import com.codecool.funstudybackend.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE c.imageUrl NOT LIKE 'null'")
    List<Card> getAllCardWithPicture();

    @Query("SELECT c FROM Card c WHERE c.imageUrl is null")
    List<Card> getAllCardWithOutPicture();

    Card findCardByWord(String word);

}
