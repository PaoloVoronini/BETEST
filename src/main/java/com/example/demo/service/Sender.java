package com.example.demo.service;

import com.example.demo.configuration.KafkaTopics;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Sender {

    private final KafkaTopics topics;
    private final KafkaTemplate<String, String> template;

    public void send(String message) {
        template.send(topics.getTopicOut(), message);
    }
}
