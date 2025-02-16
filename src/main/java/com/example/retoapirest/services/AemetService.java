package com.example.retoapirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AemetService {

    @Value("${aemet.api.key}")
    private String aemetApiKey;

    @Autowired
    private RestTemplate restTemplate;
}
