package com.example.apigtw.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class Rest2Service {
    private final RestTemplate restTemplate;
    @Autowired
    public Rest2Service(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
}
