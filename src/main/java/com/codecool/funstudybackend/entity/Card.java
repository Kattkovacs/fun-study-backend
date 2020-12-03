package com.codecool.funstudybackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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

    @Column(nullable = true)
    private String imageUrl;

    @ManyToMany(mappedBy = "unknownCards")
    Set<ApplicationUser> users;

    public void addUser(ApplicationUser user){
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId.equals(card.cardId) &&
                Objects.equals(word, card.word) &&
                Objects.equals(definition, card.definition) &&
                Objects.equals(imageUrl, card.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, word, definition, imageUrl);
    }
}
