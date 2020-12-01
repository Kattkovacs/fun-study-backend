package com.codecool.funstudybackend.repository;

import com.codecool.funstudybackend.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findUserByEmail(String email);
}
