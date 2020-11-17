package com.codecool.funstudybackend;

import com.codecool.funstudybackend.entity.User;
import com.codecool.funstudybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.codecool.funstudybackend")
@SpringBootApplication
public class FunStudyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunStudyBackendApplication.class, args);
    }

}
