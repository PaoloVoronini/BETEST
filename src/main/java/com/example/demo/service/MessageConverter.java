package com.example.demo.service;

import com.example.demo.dto.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class MessageConverter {

    private final ObjectMapper objectMapper;

    public MessageDto fromString(String s) {
        try {
            return serialize(s);
        } catch (JsonProcessingException e) {
            //TODO: error handling
            throw new RuntimeException(e);
        }
    }

    public String toString(MessageDto m) {
        try {
            return deserialize(m);
        } catch (JsonProcessingException e) {
            //TODO: error handling
            throw new RuntimeException(e);
        }
    }

    private String deserialize(MessageDto m) throws JsonProcessingException {
        return objectMapper.writeValueAsString(m);
    }

    private MessageDto serialize(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, MessageDto.class);
    }
}
