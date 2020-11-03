package com.codecool.funstudybackend.controller;

import com.codecool.funstudybackend.model.RemoteURLReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;

@RestController
public class CardController {
    @Autowired
    RemoteURLReader remoteURLReader;

    @Autowired
    ObjectMapper mapper;

    @GetMapping("/card")
    public String createCardContent() throws IOException {

        String randomWord = RandomWordGenerator.getRandomWord();
        String result = "";
        while(result.equals("")) {
            System.out.println(randomWord);
            try {
                result = remoteURLReader.readFromUrl(randomWord);
            } catch (IOException e){
                System.out.println("word not found");
                randomWord = RandomWordGenerator.getRandomWord();
            }
        }
//        ObjectNode objectNode = mapper.createObjectNode();
//        objectNode.put("definition", "a frame or structure on which something is mounted or supported, especially a sawhorse.");
//        objectNode.put("image_url", "https://media.owlbot.info/dictionary/images/ggggggggggm.jpg.400x400_q85_box-0,0,1000,1000_crop_detail.jpg");
//        objectNode.put("word", "horse");
//        return objectNode;
        return result;
    }
}