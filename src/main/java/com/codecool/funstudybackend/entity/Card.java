package com.codecool.funstudybackend.entity;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
    Set<User> users;

    public void addUser(User user){
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
                Objects.equals(imageUrl, card.imageUrl) &&
                Objects.equals(users, card.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, word, definition, imageUrl, users);
    }
}
