package com.codecool.funstudybackend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;
    private String lastName;
    private String date;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Singular
    @JoinTable(name = "unknowns")
    Set<Card> unknownCards;

    public void addUnknownCard(Card card){
        unknownCards.add(card);
    }
}