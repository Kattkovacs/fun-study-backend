package com.codecool.funstudybackend.entity;

import com.codecool.funstudybackend.security.ApplicationUserRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private ApplicationUserRole role;

    private String firstName;
    private String lastName;
    private LocalDate date;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Singular
    @JoinTable(name = "unknowns")
    Set<Card> unknownCards;

    public void addUnknownCard(Card card) {
        unknownCards.add(card);
    }
}