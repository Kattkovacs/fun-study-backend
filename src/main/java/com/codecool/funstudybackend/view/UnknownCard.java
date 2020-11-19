package com.codecool.funstudybackend.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnknownCard {
    private String word;
    private String definition;
}
