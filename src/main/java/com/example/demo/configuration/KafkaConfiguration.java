package com.example.demo.configuration;

import com.example.demo.service.Consumer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Autowired
    private Consumer consumer;

    /**
     * This config just creates topics if they don't exist.
     */
    @Configuration
    class TopicsConfiguration {

        @Bean
        public NewTopic incomingTopic(KafkaTopics topics) {
            return TopicBuilder.name(topics.getTopicIn())
                    .partitions(10)
                    .replicas(1)
                    .build();
        }

        @Bean
        public NewTopic outgoingTopic(KafkaTopics topics) {
            return TopicBuilder.name(topics.getTopicOut())
                    .partitions(10)
                    .replicas(1)
                    .build();
        }
    }

    @KafkaListener(id = "myId", topics = "${kafka.topic-in}")
    public void processIncoming(ConsumerRecord record) {
        //idempotency guaranteed by message.id
        //we could use instead partition and offset
        String message = (String) record.value();
        consumer.consume(message);
    }
}
