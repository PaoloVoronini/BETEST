package com.example.demo.service;

import com.example.demo.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Log4j2
@RequiredArgsConstructor
@Service
public class Scheduler {

    @Value("${kafka.sender}")
    private String name;
    private final Sender sender;
    private final MessageConverter messageConverter;

    @Scheduled(fixedDelay = 5000)
    void generateRandomAndSend() {
        MessageDto dto = new MessageDto(System.currentTimeMillis(), name, LocalTime.now().toString());
        String message = messageConverter.toString(dto);
        log.info("Sending: {}", message);
        sender.send(message);
    }
}
