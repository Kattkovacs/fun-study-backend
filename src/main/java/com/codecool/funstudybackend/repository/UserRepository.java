package com.codecool.funstudybackend.repository;

import com.codecool.funstudybackend.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findApplicationUserByEmail(String email);

    List<ApplicationUser> findApplicationUserByActive(Boolean active);
}
