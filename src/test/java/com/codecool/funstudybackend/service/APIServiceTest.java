package com.codecool.funstudybackend.service;

import com.codecool.funstudybackend.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.codecool.funstudybackend.model.RemoteURLReader;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class APIServiceTest {
    @Autowired
    APIService apiService;

    @Autowired
    Utility utility;

    @Mock
    RemoteURLReader remoteURLReader;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testMyAssertEquals() throws JSONException {
        String expected = "{\"word\":\"folk\",\"image_url\":null,\"definition\":\"folk music.\"}";
        String actual = "{\"pronunciation\":\"fōk\",\"word\":\"folk\",\"definitions\":[{\"emoji\":null,\"image_url\":null,\"definition\":\"folk music.\",\"type\":\"noun\",\"example\":\"a mixture of folk and reggae\"}]}";;
        JSONObject json1 = new JSONObject(expected);
        JSONObject json2 = new JSONObject(actual);
        assertTrue(utility.myAssertEquals(json1, json2));
    }


    @Test
    void testAskForCardJsonWithWordGivesJSON() throws JSONException {
        String expectedString = "{\"word\":\"folk\",\"image_url\":null,\"definition\":\"folk music.\"}";
        JSONObject expected = new JSONObject(expectedString);
        try {
            when(remoteURLReader.readFromUrl("folk")).thenReturn(expected);
            utility.myAssertEquals(expected, apiService.askForCardJson("folk"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAskForCardJsonWithWrongWordFindNew() {
        String mockWord = "asdf";
        JSONObject result = apiService.askForCardJson(mockWord);
        assertNotNull(result);
    }

    @Test
    void testFindCardContentFromResultReturnAsExpected() throws JSONException {
        String jsonString = "{\"pronunciation\":\"fōk\",\"word\":\"folk\",\"definitions\":[{\"emoji\":null,\"image_url\":null,\"definition\":\"folk music.\",\"type\":\"noun\",\"example\":\"a mixture of folk and reggae\"}]}";
        JSONObject json = new JSONObject(jsonString);
        String image = null;

        ObjectNode expectedObjectNode = mapper.createObjectNode();
        expectedObjectNode.put("word",  "folk");
        expectedObjectNode.put("definition", "folk music.");
        expectedObjectNode.put("image_url", image);

        assertEquals(expectedObjectNode, apiService.findCardContentFromResult(json));
    }

    @Test
    void testFindCardContentFromResultWithPicture() throws JSONException {
        String jsonString = "{\"pronunciation\":\"fōk\",\"word\":\"rabbit\",\"definitions\":[{\"emoji\":null,\"image_url\":\"https://media.owlbot.info/dictionary/images/mmmc.jpg\",\"definition\":\"a gregarious burrowing plant-eating mammal, with long ears, long hind legs, and a short tail.\",\"type\":\"noun\",\"example\":\"a mixture of folk and reggae\"}]}";
        JSONObject json = new JSONObject(jsonString);
        String image = "https://media.owlbot.info/dictionary/images/mmmc.jpg";

        ObjectNode expectedObjectNode = mapper.createObjectNode();
        expectedObjectNode.put("word",  "rabbit");
        expectedObjectNode.put("definition", "a gregarious burrowing plant-eating mammal, with long ears, long hind legs, and a short tail.");
        expectedObjectNode.put("image_url", image);

        assertEquals(expectedObjectNode, apiService.findCardContentFromResult(json));
    }

}