package com.example.assignment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    ObjectMapper mapper(){
        return new ObjectMapper();
    }
}
