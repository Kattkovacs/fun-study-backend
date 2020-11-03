package com.codecool.funstudybackend.model;

import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class RemoteURLReader {
    private static String cardURL = "https://owlbot.info/api/v4/dictionary/%s?format=json";

    public String readFromUrl(String word) throws IOException {
        String path = String.format(cardURL, word);
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Token d9babcf6a7b2f35f7cf176123956ef6dbe4b5585");
        conn.setRequestProperty("Content-Type", "application/json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String lines = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        return lines;
    }
}
