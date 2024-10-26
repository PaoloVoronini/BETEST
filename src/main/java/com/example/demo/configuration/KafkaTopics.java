package com.example.demo.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka")
@Data
public class KafkaTopics {

    String topicIn;
    String topicOut;
}
