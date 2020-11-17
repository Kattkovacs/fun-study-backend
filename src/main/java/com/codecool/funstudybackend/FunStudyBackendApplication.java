package com.codecool.funstudybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class FunStudyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FunStudyBackendApplication.class, args);
    }

}
