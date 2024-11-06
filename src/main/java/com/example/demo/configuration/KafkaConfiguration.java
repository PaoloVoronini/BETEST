package com.example.demo.configuration;

import com.example.demo.dto.MessageDto;
import com.example.demo.service.Consumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
public class KafkaConfiguration {

    @Autowired
    private Consumer consumer;

    @Bean
    public ByteArrayJsonMessageConverter converter(ObjectMapper objectMapper) {
        return new ByteArrayJsonMessageConverter(objectMapper);
    }

    @KafkaListener(id = "myId", topics = "${kafka.topic-in}")
    public void processIncoming(@Payload MessageDto message) {
        //idempotency guaranteed by message.id
        //we could use instead partition and offset
        consumer.consume(message);
    }
}
