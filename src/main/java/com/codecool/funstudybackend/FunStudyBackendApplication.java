package com.codecool.funstudybackend;

import com.codecool.funstudybackend.repository.CardRepository;
import com.codecool.funstudybackend.util.DbInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.codecool.funstudybackend")
public class FunStudyBackendApplication {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private DbInit dbInit;

    public static void main(String[] args) {
        SpringApplication.run(FunStudyBackendApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {
            dbInit.initDb(10);
        };
    }

}
