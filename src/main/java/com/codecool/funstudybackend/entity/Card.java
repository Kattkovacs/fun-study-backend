package com.codecool.funstudybackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Card {

    @Id
    @GeneratedValue
    private Long cardId;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private String definition;

    @Column()
    private String image_url;


}
