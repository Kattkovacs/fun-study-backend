package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.repository.CardRepository;
import com.codecool.funstudybackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCardEndpoint() throws Exception {
        mockMvc.perform(get("/card"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.word").exists())
                .andExpect(jsonPath("$.definition").exists());
    }


//    @InjectMocks
//    CardController cardController;
//
//    @Mock
//    CardRepository cardRepository;
//
//    @Mock
//    UserRepository userRepository;
//
//
//    @Test
//    public void testCardEndpoint() throws IOException {
//        Card responseCard = Card.builder()
//                .word("fish").definition("a limbless cold-blooded vertebrate animal with gills and fins living wholly in water.").build();
//        when(cardRepository.findAll()).thenReturn(List.of(responseCard));
//        Card testCard = cardController.createCardContent();
//        verify(cardRepository).findAll(); //check if function is called
//        verify(cardRepository,times(1)).findAll(); //check if function is called only once
//        verifyNoMoreInteractions(cardRepository);
//        assertThat(testCard.equals(responseCard));
//
//
//
//    }

}