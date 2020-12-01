package com.codecool.funstudybackend.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {
    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private LocalDate date;

}
