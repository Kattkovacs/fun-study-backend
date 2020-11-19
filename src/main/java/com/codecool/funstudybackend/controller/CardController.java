package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.view.UnknownCard;
import com.codecool.funstudybackend.repository.CardRepository;
import com.codecool.funstudybackend.repository.UserRepository;
import com.codecool.funstudybackend.service.RemoteURLReader;
import com.codecool.funstudybackend.entity.User;
import com.codecool.funstudybackend.service.APIService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
@CrossOrigin
@RestController
@SessionAttributes("user")
public class CardController {

    @Autowired
    RemoteURLReader remoteURLReader;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    APIService apiService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/card-with-picture")
    public Card createCardWithPictureContent() throws IOException {
        Random random = new Random();
        List<Card> cardsWithPicture = cardRepository.getAllCardWithPicture();
        return cardsWithPicture.get(random.nextInt(cardsWithPicture.size()));
    }

    @GetMapping("/card-without-picture")
    public Card createCardWithOutPictureContent() throws IOException {
        Random random = new Random();
        List<Card> cardsWithoutPicture = cardRepository.getAllCardWithOutPicture();
        return cardsWithoutPicture.get(random.nextInt(cardsWithoutPicture.size()));
    }

    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public User registration(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @ModelAttribute("user")
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public User login(@RequestBody User user, Model model) {
        User userByEmailAndPassword = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        model.addAttribute(userByEmailAndPassword);
        return userByEmailAndPassword;
    }

    @PostMapping(value = "/savecard", consumes = "application/json", produces = "application/json")
    public Card saveCard(@RequestBody UnknownCard unknownCard, @ModelAttribute("user") User user){
        System.out.println("User email: " + user.getEmail());
        return cardRepository.findCardByWord(unknownCard.getWord());
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String testMestod(){
//        ShoppingCart cart = (ShoppingCart)request.getSession().setAttribute("cart",value);
//        return "testJsp";
//    }
}