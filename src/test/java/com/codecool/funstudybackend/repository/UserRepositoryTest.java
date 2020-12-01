package com.codecool.funstudybackend.repository;

import com.codecool.funstudybackend.entity.ApplicationUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testIfFindUserByEmailAndPassword(){
        ApplicationUser test1 = ApplicationUser.builder()
                .email("test1@codecool.com")
                .password("test")
                .build();

        ApplicationUser test2 = ApplicationUser.builder()
                .email("test2@codecool.com")
                .password("test")
                .build();

        userRepository.save(test1);
        userRepository.save(test2);

        ApplicationUser userByEmailAndPassword = userRepository.findUserByEmailAndPassword("test2@codecool.com", "test");
        assertEquals(userByEmailAndPassword, test2);
    }

}