package com.example.demo.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
public class AppConfiguration {

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
