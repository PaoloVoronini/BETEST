package com.example.demo.service;

import com.example.demo.dao.Message;
import com.example.demo.dao.MessageRepository;
import com.example.demo.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class Consumer {

    private final MessageRepository messageRepository;
    private final MessageConverter messageConverter;

    public void consume(String received) {
        log.info("Received: {}", received);
        MessageDto dto = messageConverter.fromString(received);
        Message message = fromDto(dto);
        messageRepository.save(message);
    }

    private static Message fromDto(MessageDto dto) {
        return Message.builder()
                .id(dto.id())
                .sender(dto.sender())
                .text(dto.text())
                .build();
    }
}
