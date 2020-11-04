package com.codecool.funstudybackend.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RemoteURLReaderTest {

    @Autowired
    RemoteURLReader remoteURLReader;

    @Test
    void testRemoteURLReaderWithWordGivesJSON() throws IOException, JSONException {
        String testWord = "folk";
        String expectedString = "{\"pronunciation\":\"fōk\",\"word\":\"folk\",\"definitions\":[{\"emoji\":null,\"image_url\":null,\"definition\":\"folk music.\",\"type\":\"noun\",\"example\":\"a mixture of folk and reggae\"}]}";
        JSONObject expected = new JSONObject(expectedString);
        JSONObject result = remoteURLReader.readFromUrl(testWord);
        assertEquals(expected.toString(), result.toString());
    }

    @Test
    void testRemoteURLReaderWithWrongWordThrowIOException() throws IOException, JSONException {
        String testWord = "asdf";
        assertThrows(IOException.class, () -> remoteURLReader.readFromUrl(testWord));
    }

}