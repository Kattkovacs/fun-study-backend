package com.codecool.funstudybackend.service;

import com.codecool.funstudybackend.model.RemoteURLReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class APIService {
    @Autowired
    RemoteURLReader remoteURLReader;

    @Autowired
    ObjectMapper mapper;

    private JSONObject askForCardJson(){
        String randomWord = RandomWordGenerator.getRandomWord();
        JSONObject result = null;
        while(result == null) {
            try {
                result = remoteURLReader.readFromUrl(randomWord);
            } catch (IOException e){
                System.out.println("word not found");
                randomWord = RandomWordGenerator.getRandomWord();
            }
        }
        return result;
    }

    public ObjectNode findCardContentFromResult(){
        JSONObject result = askForCardJson();

        ObjectNode objectNode = mapper.createObjectNode();

        JSONArray definitions = result.getJSONArray("definitions");
        JSONObject definitonContainer = definitions.getJSONObject(0);
        String definition = definitonContainer.getString("definition");
        String imageUrl = null;

        if(!definitonContainer.isNull("image_url")) {
            imageUrl = definitonContainer.getString("image_url");
        }

        objectNode.put("word",  result.getString("word"));
        objectNode.put("definition", definition);
        objectNode.put("image_url", imageUrl);
        return objectNode;
    }


}
