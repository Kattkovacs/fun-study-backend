package com.codecool.funstudybackend.repository;

import com.codecool.funstudybackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmailAndPassword(String email, String password);
    User findUserByEmail(String email);
}
