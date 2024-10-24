package com.example.demo.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic incomingTopic() {
        return TopicBuilder.name("fromUser")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @KafkaListener(id = "myId", topics = "fromUser")
    public void listen(String in) {
        System.out.println(in);
    }

    @Bean
    public NewTopic outgoingTopic() {
        return TopicBuilder.name("toUser")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
            template.send("toUser", "test");
        };
    }
}
