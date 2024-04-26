package com.example.apigtw.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Rest1Service {
    
    private final RestTemplate restTemplate;
    @Autowired
    public Rest1Service(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    
}
