package com.codecool.funstudybackend.repository;

import com.codecool.funstudybackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
