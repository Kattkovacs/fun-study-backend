package com.codecool.funstudybackend.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class APIServiceTest {
    @Autowired
    APIService apiService;

    @Test
    void testAskForCardJsonWithWordGivesJSON() throws JSONException {
        String mockWord = "folk";
        String expectedString = "{\"pronunciation\":\"fōk\",\"word\":\"folk\",\"definitions\":[{\"emoji\":null,\"image_url\":null,\"definition\":\"folk music.\",\"type\":\"noun\",\"example\":\"a mixture of folk and reggae\"}]}";
        JSONObject expected = new JSONObject(expectedString);
        JSONObject result = apiService.askForCardJsonMock(mockWord);
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void testAskForCardJsonWithWrongWordGivesJSON() {
        String mockWord = "asdf";
        JSONObject result = apiService.askForCardJsonMock(mockWord);
        assertNotNull(result);
    }

}