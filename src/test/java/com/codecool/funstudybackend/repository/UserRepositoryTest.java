package com.codecool.funstudybackend.repository;

import com.codecool.funstudybackend.entity.User;
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
        User test1 = User.builder()
                .email("test1@codecool.com")
                .password("test")
                .build();

        User test2 = User.builder()
                .email("test2@codecool.com")
                .password("test")
                .build();

        userRepository.save(test1);
        userRepository.save(test2);

        User userByEmailAndPassword = userRepository.findUserByEmailAndPassword("test2@codecool.com", "test");
        assertEquals(userByEmailAndPassword, test2);
    }

}