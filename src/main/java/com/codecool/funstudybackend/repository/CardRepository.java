package com.codecool.funstudybackend.repository;

import com.codecool.funstudybackend.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
