package com.codecool.funstudybackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class APIService {
    @Autowired
    RemoteURLReader remoteURLReader;

    @Autowired
    ObjectMapper mapper;

    public JSONObject askForCardJson(String randomWord) {
        JSONObject result = null;
        while (result == null) {
            try {
                result = remoteURLReader.readFromUrl(randomWord);
                System.out.println("word found: " + randomWord);
            } catch (IOException e) {
                System.out.println("word not found: " + randomWord);
                randomWord = RandomWordGenerator.getRandomWord();
            }
        }
        return result;
    }

    public JSONObject askForCardJsonWithImg(String randomWord) {
        JSONObject result = null;
        Boolean imgUrl = null;
        while (result == null || imgUrl == null) {
            try {
                result = remoteURLReader.readFromUrl(randomWord);
                JSONArray definitions = result.getJSONArray("definitions");
                JSONObject definitionContainer = definitions.getJSONObject(0);
                if (!definitionContainer.isNull("image_url")) {
                    imgUrl = true;
                }
                System.out.println("word found: "+ randomWord);
            } catch (IOException e) {
                System.out.println("word not found: "+ randomWord);
                randomWord = RandomWordGenerator.getRandomWord();
            }
        }
        return result;
    }


    public ObjectNode findCardContentFromResult(JSONObject result) {
        ObjectNode objectNode = mapper.createObjectNode();

        JSONArray definitions = result.getJSONArray("definitions");
        JSONObject definitionContainer = definitions.getJSONObject(0);
        String definition = definitionContainer.getString("definition");
        String imageUrl = null;

        if (!definitionContainer.isNull("image_url")) {
            imageUrl = definitionContainer.getString("image_url");
        }

        objectNode.put("word", result.getString("word"));
        objectNode.put("definition", definition);
        objectNode.put("image_url", imageUrl);
        return objectNode;
    }


}
