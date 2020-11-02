package com.codecool.funstudybackend.service;

import com.codecool.funstudybackend.model.RemoteURLReader;
import org.springframework.stereotype.Component;

@Component
public class APIService {

    private static final String apiPath = "https://run.mocky.io/v3/9e14e086-84c2-4f98-9e36-54928830c980?stock=%s";

    RemoteURLReader remoteURLReader;

    public APIService(RemoteURLReader remoteURLReader) {
            this.remoteURLReader = remoteURLReader;
        }
}
