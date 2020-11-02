package com.codecool.funstudybackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    @Autowired
    ObjectMapper mapper;

    @GetMapping("/")
    public ObjectNode mainPageMaker() {
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("definition", "a frame or structure on which something is mounted or supported, especially a sawhorse.");
        objectNode.put("image_url", "https://media.owlbot.info/dictionary/images/ggggggggggm.jpg.400x400_q85_box-0,0,1000,1000_crop_detail.jpg");
        objectNode.put("word", "horse");
        return objectNode;
    }
}