package com.codecool.funstudybackend.entity;

import com.codecool.funstudybackend.security.ApplicationUserRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<ApplicationUserRole> roles;

    private String firstName;
    private String lastName;
    private LocalDate date;
    private Boolean active;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @Singular
    @JoinTable(name = "unknowns")
    Set<Card> unknownCards;

    public void addUnknownCard(Card card) {
        unknownCards.add(card);
    }
}